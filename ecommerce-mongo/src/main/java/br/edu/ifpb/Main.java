package br.edu.ifpb;

import br.edu.ifpb.Dao.ClienteDao;
import br.edu.ifpb.Dao.ProdutoDAO;
import br.edu.ifpb.model.Cliente;
import br.edu.ifpb.model.Compra;
import br.edu.ifpb.model.ItemCompra;
import br.edu.ifpb.model.Produto;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.math.BigDecimal;
import java.util.Scanner;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


  
        public static void main(String[] args) {

            CodecRegistry pojoCodecRegistry = fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );
            try (MongoClient mongoClient=MongoClients.create(
                    MongoClientSettings.builder()
                            .applyConnectionString(new ConnectionString("mongodb://10.13.132.43:27017"))
                            .codecRegistry(pojoCodecRegistry).build())){


                MongoDatabase mongoDatabase=mongoClient.getDatabase("ecommerce");
                ClienteDao clienteDao=new ClienteDao(mongoDatabase);
                ProdutoDAO produtoDAO=new ProdutoDAO(mongoDatabase);



                int opcao=0;
                do{

                    System.out.println("""
                            1-cadastrar cliente
                            2-listar clientes
                            3-casdatrar produto
                            4-listar produtos
                            5-nova compra
                            6-listar compras de um cliente
                            0-sair
                            """);
                    opcao=new Scanner(System.in).nextInt();
                    switch (opcao){
                        case 1 ->{
                            System.out.println("1-informe o nome do cliente");
                            String nome=new Scanner(System.in).nextLine();
                            System.out.println("informe o cpf");
                            String cpf=new Scanner(System.in).nextLine();
                            Cliente cliente= new Cliente(nome,cpf);
                            clienteDao.salvar(cliente);
                            System.out.println("cadastrado com sucesso");


                        }
                        case 2 -> {
                            clienteDao.buscarTodos().forEach(cliente -> {
                                System.out.println("id:" + cliente.getId() + " " + "nome:" + cliente.getNome() + " " + "cpf:" + cliente.getCpf());
                            });

                        }
                        case 3 -> {
                            System.out.println("1-informe a descricao do produto");
                            String descricao=new Scanner(System.in).nextLine();
                            System.out.println("informe o valor");
                            BigDecimal valor=new Scanner(System.in).nextBigDecimal();
                             Produto produto= new Produto(descricao,valor);
                            produtoDAO.salvarProduto(produto);
                            System.out.println("Produto cadastrado com sucesso");

                        }
                        case 4 -> {
                            produtoDAO.buscarTodos().forEach(produto -> {
                                System.out.println("id:" + produto.getId()+ " " + "descriçao:" + produto.getDescricao()+ " " + "valor:" + produto.getValor());
                            });


                        }
                        case 5 -> {
                            System.out.println("digite o cpf do cliente");
                            String cpfcliente=new Scanner(System.in).nextLine();
                            Cliente cliente=clienteDao.buscarPorCPF(cpfcliente);
                            if(cliente == null){
                                System.out.println("CLiennte nao encontrado");
                                return;
                            }

                            while (true){
                                System.out.println("digite o id d produto ou(vazio para finalizar)");

                                String idProduto=new Scanner(System.in).nextLine();
                                if(idProduto.isBlank()){
                                    break;
                                }
                               Produto produto=produtoDAO.buscarPorId(idProduto);
                                if(produto==null){
                                    System.out.println("produto nao encontrado");
                                    continue;
                                }
                                System.out.println("Quantidade");
                                int quantidade= new Scanner(System.in).nextInt();
                                Compra compra = new Compra();
                                compra.setCliente(cliente);
                                compra.addItem(new ItemCompra(produto,quantidade, produto.getValor()));

                            }
                        }
                        case 6 -> System.out.println("6-listar compras de um cliente");

                        case 0-> System.out.println( "0-sair");
                    }
                }while (opcao != 0);

            }
        }
}