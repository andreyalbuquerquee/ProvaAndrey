import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.Text;
import java. text.Format;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {   
        int option;
        Scanner in = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Product> productList = new ArrayList<>();
        List<Sale> salesList = new ArrayList<>();
        
        
        
        



        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Incluir produto\n2 - Consultar produto\n3 - Listagem de produtos\n4 - Vendas por período - detalhado\n5 - Realizar venda\n0 - Sair ");
            option = in.nextInt();
            in.nextLine();
            
            switch(option) {
                case 1:
                System.out.println("Digite o nome do produto: \n");
                String name = in.next();
                in.nextLine();
                
                System.out.println("Informe o código do produto: \n");
                String code = in.next();
                in.nextLine();
                
                System.out.println("Informe o preço do produto: \n");
                Double price = in.nextDouble();
                in.nextLine();
                
                System.out.println("Informe a quantidade em estoque \n");
                int stock = in.nextInt();
                in.nextLine();

                productList.add(new Product(name, code, price, stock));
                System.out.println("Produto cadastrado com sucesso. \n");
                
                backMenu(in);



                break;
                case 2:
                System.out.println("Informe o código do produto: \n");
                String informedCode = in.next();

                List<Product> informedProduct = productList.stream()
                .filter(p ->  p .getCode().contains(informedCode)).collect(Collectors.toList());; //Bota o produto em um array

                if(informedProduct.isEmpty()) { // Verifica se o produto informado não existe
                    System.out.println("Produto não encontrado! \n");
                } else {
                    System.out.println(informedProduct.get(0));
                }
                
                in.nextLine();
                backMenu(in);

                break;
                case 3:
                System.out.println("\n****\nProdutos\n****\n");

                for (Product produto : productList) {
                    System.out.println(produto);
                }

                DoubleSummaryStatistics prices = productList.stream()
                .collect(Collectors.summarizingDouble(Product::getPrice)); // Pega os preços e taca em um array
                System.out.println("Média dos preços: R$" + prices.getAverage() + "\n");
                System.out.println("Maior preço: R$" + prices.getMax() + "\n");
                System.out.println("Menor preço: R$" + prices.getMin() + "\n");

                
                in.nextLine();
                backMenu(in);

                break;
                case 4:
                if (salesList.isEmpty()) {
                    System.out.println("Não foram feitas vendas por enquanto.");
                } else {

                System.out.println("Informe a data inicial\n");
                LocalDate initialDate = LocalDate.parse(in.next(),dateFormatter);
                in.nextLine();
                System.out.println("Informe a data final: \n");
                LocalDate finalDate = LocalDate.parse(in.next(), dateFormatter);
                in.nextLine();
                
                System.out.println("\n****\nRelatório de Vendas\n****\n");
                System.out.println(String.format("De: %s\nPara: %s\n", initialDate, finalDate));

                Double totalValue = 0.0;
                List<Sale> periodSales = new ArrayList<>();

                for (Sale sale : salesList) {
                    if (sale.getDate().isAfter(initialDate) && sale.getDate().isBefore(finalDate)) {
                        periodSales.add(sale);
                        System.out.println(sale);
                        totalValue += sale.getValueSold();
                    }
                }
                int lengthSales = periodSales.size();
                Double average = totalValue / lengthSales;
                System.out.println("A média das vendas é: " + average);
                
                }

                backMenu(in);

                
                break;
                case 5:
                System.out.println("Informe o código do produto vendido\n");
                String productSoldCode = in.next();
                in.nextLine();
                
                List<Product> productSoldList = productList.stream()
                .filter(p ->  p .getCode().equals(productSoldCode)).collect(Collectors.toList());; //Bota o produto em um array

                if(productSoldList.isEmpty()) { // Verifica se o produto informado não existe
                    System.out.println("Produto não encontrado! \n");
                } else {
                    Product productSold = productSoldList.get(0);
                    price = productSold.getPrice();
                    
                    System.out.println("Informe a data da venda: \n");
                    String date = in.next();
                    LocalDate saleDate = LocalDate.parse(date,dateFormatter);
                    
                    
                    System.out.println("Informe a quantidade vendida: \n");
                    int uniSolds = in.nextInt();
                    Double valueSold = price * uniSolds;
                    in.nextLine();
                    
                    if (uniSolds > productSold.getStock()){
                        System.out.println("Não há produtos o suficiente em estoque \n");
                    } else {
                        Sale sale = new Sale(productSold, saleDate, uniSolds, valueSold);
                        salesList.add(sale);
                        salesList.sort(Comparator.comparing(s -> s.getDate()));
                        productSold.setStock(productSold.getStock() - sale.getUni());
                        System.out.println("Parabéns pela venda!! \n");
                    }
                }

                in.nextLine();
                backMenu(in);

                break;
                case 0:
                System.out.println("Saindo do programa... \n");

                break;
                default:
                System.out.println("Opção inválida! \n");


            }

        } while(option != 0);

    }


    private static void backMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}











