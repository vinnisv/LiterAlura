package br.com.alura.literalura;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.GutendexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexClient gutendexClient;

	@Autowired
	private LivroRepository livroRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			exibirMenu();
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar buffer

			switch (opcao) {
				case 1:
					System.out.println("Digite o título do livro:");
					String titulo = scanner.nextLine();
					try {
						Livro livro = gutendexClient.buscarLivroPorTitulo(titulo);
						if (livro != null) {
							livroRepository.save(livro);
							System.out.println("Livro salvo: " + livro);
						} else {
							System.out.println("Livro não encontrado.");
						}
					} catch (Exception e) {
						System.out.println("Erro: " + e.getMessage());
					}
					break;
				case 2:
					System.out.println("Livros cadastrados:");
					livroRepository.findAll().forEach(System.out::println);
					break;
				case 3:
					System.out.println("Listar autores (não implementado).");
					break;
				case 4:
					System.out.println("Buscar livros por idioma (não implementado).");
					break;
				case 5:
					System.out.println("Saindo...");
					scanner.close();
					return;
				default:
					System.out.println("Opção inválida.");
			}
		}
	}

	private void exibirMenu() {
		System.out.println("\n=== LiterAlura ===");
		System.out.println("1. Buscar e salvar livro por título");
		System.out.println("2. Listar todos os livros");
		System.out.println("3. Listar autores");
		System.out.println("4. Buscar livros por idioma");
		System.out.println("5. Sair");
		System.out.print("Escolha uma opção: ");
	}
}