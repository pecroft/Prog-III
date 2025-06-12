package TDE1;

import java.io.*;
import java.util.*;

public class CadastroAutomoveis {
    private static final String ARQUIVO = "automoveis.txt";
    private static ArrayList<Automovel> lista = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados();

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1 - Incluir automóvel");
            System.out.println("2 - Remover automóvel");
            System.out.println("3 - Alterar dados de automóvel");
            System.out.println("4 - Consultar automóvel por placa");
            System.out.println("5 - Listar automóveis (ordenado)");
            System.out.println("6 - Salvar e sair");
            System.out.print(">> ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> incluirAutomovel(sc);
                case 2 -> removerAutomovel(sc);
                case 3 -> alterarAutomovel(sc);
                case 4 -> consultarAutomovel(sc);
                case 5 -> listarAutomoveis(sc);
                case 6 -> salvarDados();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }

    private static void incluirAutomovel(Scanner sc) {
        System.out.print("Placa: ");
        String placa = sc.nextLine();

        if (buscarPorPlaca(placa) != null) {
            System.out.println("Erro: placa já cadastrada!");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        lista.add(new Automovel(placa, modelo, marca, ano, valor));
        System.out.println("Automóvel adicionado com sucesso.");
    }

    private static void removerAutomovel(Scanner sc) {
        System.out.print("Digite a placa: ");
        String placa = sc.nextLine();
        Automovel auto = buscarPorPlaca(placa);

        if (auto == null) {
            System.out.println("Automóvel não encontrado.");
            return;
        }

        lista.remove(auto);
        System.out.println("Automóvel removido.");
    }

    private static void alterarAutomovel(Scanner sc) {
        System.out.print("Digite a placa: ");
        String placa = sc.nextLine();
        Automovel auto = buscarPorPlaca(placa);

        if (auto == null) {
            System.out.println("Automóvel não encontrado.");
            return;
        }

        System.out.print("Novo modelo: ");
        auto.setModelo(sc.nextLine());
        System.out.print("Nova marca: ");
        auto.setMarca(sc.nextLine());
        System.out.print("Novo ano: ");
        auto.setAno(sc.nextInt());
        System.out.print("Novo valor: ");
        auto.setValor(sc.nextDouble());
        sc.nextLine(); 

        System.out.println("Dados atualizados.");
    }

    private static void consultarAutomovel(Scanner sc) {
        System.out.print("Digite a placa: ");
        String placa = sc.nextLine();
        Automovel auto = buscarPorPlaca(placa);

        if (auto == null) {
            System.out.println("Automóvel não encontrado.");
        } else {
            System.out.println(auto);
        }
    }

    private static void listarAutomoveis(Scanner sc) {
        System.out.print("Ordenar por (1-Placa, 2-Modelo, 3-Marca): ");
        int tipo = sc.nextInt();
        sc.nextLine(); 

        Comparator<Automovel> comparator;
        switch (tipo) {
            case 1 -> comparator = Comparator.comparing(Automovel::getPlaca);
            case 2 -> comparator = Comparator.comparing(Automovel::getModelo);
            case 3 -> comparator = Comparator.comparing(Automovel::getMarca);
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        lista.stream()
             .sorted(comparator)
             .forEach(System.out::println);
    }

    private static void carregarDados() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String placa = partes[0];
                String modelo = partes[1];
                String marca = partes[2];
                int ano = Integer.parseInt(partes[3]);
                double valor = Double.parseDouble(partes[4]);
                lista.add(new Automovel(placa, modelo, marca, ano, valor));
            }
        } catch (FileNotFoundException e) {
            System.out.println("(Arquivo não encontrado, será criado ao salvar.)");
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    private static void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Automovel a : lista) {
                bw.write(a.toCSV());
                bw.newLine();
            }
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private static Automovel buscarPorPlaca(String placa) {
        for (Automovel a : lista) {
            if (a.getPlaca().equalsIgnoreCase(placa)) {
                return a;
            }
        }
        return null;
    }
}
