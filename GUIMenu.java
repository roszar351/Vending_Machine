import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.*;
/**
* Creates the GUI for Vending Machine
*/
public class GUIMenu extends Application
{
	private VendingMachine machine;
	private Button backButton;
	private Stage primaryStage;
	
	/**
	* start() - overrides the start method in Application 
	*/
	@Override
	public void start(Stage ps) throws IOException
	{
		primaryStage = ps;
		machine = new VendingMachine();
		
		Scene userMenu = createUserMenu();
		
		backButton = new Button("Back");
		backButton.setOnAction(e -> primaryStage.setScene(userMenu));
		
		primaryStage.setScene(userMenu);
		primaryStage.setTitle("Vending Machine");
		primaryStage.show();
		
		primaryStage.setOnHiding(e -> {
			try
			{
				WriteToFiles.writeStock(machine.getProducts());
				WriteToFiles.writeCoins(machine.coins.getSet());
			}
			catch(IOException ex)
			{
				System.out.println("ERROR: Failed to save data.\n" + ex.getMessage());
			}
		});
	}
	/**
	* createUserMenu() - Creates the UserMenu Scene
	* @returns Scene - returns the scene to the primaryStage
	*/
	public Scene createUserMenu()
	{
		// Main menu for user
		HBox buttonsBox = new HBox(2);
		buttonsBox.setAlignment(Pos.CENTER);
		Button show = new Button("Show products");
		Button insert = new Button("Insert coin");
		Button buy = new Button("Buy");
		Button login = new Button("Admin menu");
		Button quit = new Button("Quit");
		buttonsBox.getChildren().addAll(show, insert, buy, login, quit);
		
		show.setOnAction(e -> primaryStage.setScene(createShowProductsWindow()));
		insert.setOnAction(e -> primaryStage.setScene(createInsertCoinWindow()));
		buy.setOnAction(e -> primaryStage.setScene(createBuyWindow()));
		login.setOnAction(e -> primaryStage.setScene(createLoginWindow()));
		quit.setOnAction(e -> primaryStage.hide());
		
		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(buttonsBox);
		borderPane.setCenter(new Text("Hello please select an option: "));
		
		Scene mainScene = new Scene(borderPane, 500, 300);
		return mainScene;
	}
	/**
	* createAdminMenu() - Creates the operator scene
	* @returns Scene - returns the mainScene to primaryStage
	*/
	public Scene createAdminMenu()
	{
		// Main menu for operator
		HBox buttonsBox = new HBox(2);
		buttonsBox.setAlignment(Pos.CENTER);
		Button addProduct = new Button("Add product");
		Button removeCoins = new Button("Remove coins");
		//Button quit = new Button("Quit");
		buttonsBox.getChildren().addAll(addProduct, removeCoins, backButton);
		
		addProduct.setOnAction(e -> primaryStage.setScene(createAddProductWindow()));
		removeCoins.setOnAction(e -> primaryStage.setScene(createRemoveCoinsWindow()));
		
		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(buttonsBox);
		borderPane.setCenter(new Text("Hello please select an option: "));
		
		Scene mainScene = new Scene(borderPane, 500, 300);
		return mainScene;
	}
	/**
	* createLoginWindow() creates the Login Scene
	* @returns Scene 
	*/
	public Scene createLoginWindow()
	{
		// Login menu
		HBox loginBox = new HBox(2);
		loginBox.setAlignment(Pos.CENTER);
		Button loginButton = new Button("Login");
		//Button backButton = new Button("Back");
		loginBox.getChildren().addAll(loginButton, backButton);
		
		HBox loginBox2 = new HBox(2);
		Label label = new Label("Enter Code: ");
		TextField tfOperatorCode = new TextField();
		loginBox2.getChildren().addAll(label, tfOperatorCode);
		loginBox2.setAlignment(Pos.CENTER);
		
		loginButton.setOnAction(e -> {
			try
			{
				if(machine.login(tfOperatorCode.getText()))
				{
					primaryStage.setScene(createAdminMenu());
				}
			}
			catch(IOException ex)
			{
				messageWindow("ERROR: Failed to load operator code\n" + ex.getMessage(), createLoginWindow());
			}
		});
		
		BorderPane borderLogin = new BorderPane();
		borderLogin.setBottom(loginBox);
		borderLogin.setCenter(loginBox2);
		
		Scene login = new Scene(borderLogin, 500, 300);
		return login;
	}
	/**
	* createShowProductsWindow() makes the showProductScene
	* @returns Scene
	*/
	public Scene createShowProductsWindow()
	{
		// Show products window	
		String temp = "";
		for(Item p : machine.getProductTypes())
			temp += p.getProduct().toString() + "\n";
	
		Text productsText = new Text(temp);
		productsText.setFont(new Font(16));
		
		ScrollPane sp = new ScrollPane();
		sp.setContent(productsText);
		
		HBox productsBox = new HBox();
		//Button backButton = new Button("Back");
		productsBox.setAlignment(Pos.CENTER);
		productsBox.getChildren().add(backButton);
		
		BorderPane borderProducts = new BorderPane();
		borderProducts.setCenter(sp);
		borderProducts.setBottom(productsBox);
		
		Scene showProducts = new Scene(borderProducts, 500, 300);
		return showProducts;
	}
	/**
	* createInsertCoinWindow() creates the insertCoin Scene
	* @returns Scene
	*/
	public Scene createInsertCoinWindow()
	{
		// Insert coin menu
		String temp = "";
		char ch = 'A';
		for(Coin c : VendingMachineMenu.getCoins())
		{
			temp += ch + ") " + c + "\n";
			ch += 1;
		}
		Text coinsText = new Text(temp);
		coinsText.setFont(new Font(16));
		
		ScrollPane sp = new ScrollPane();
		sp.setContent(coinsText);
		
		HBox coinBox = new HBox();
		Button insertButton = new Button("Insert coin");
		coinBox.setAlignment(Pos.CENTER);
		coinBox.getChildren().addAll(insertButton, backButton);
		
		HBox inputBox = new HBox();
		Label inputLabel = new Label("Which coin: ");
		TextField coinChoice = new TextField();
		inputBox.getChildren().addAll(inputLabel, coinChoice);
		inputBox.setAlignment(Pos.CENTER);
		
		insertButton.setOnAction(e -> {
			if(coinChoice.getText().length() == 1)
			{
				int index = coinChoice.getText().toUpperCase().charAt(0) - 'A';
				if(index >= 0 && index < VendingMachineMenu.getCoins().length)
				{
					machine.addCoin(VendingMachineMenu.getCoins()[index]);
					coinChoice.setText("");
					messageWindow("Coin added.", createInsertCoinWindow());
				}
			}
			
		});
		
		BorderPane borderInsert = new BorderPane();
		borderInsert.setCenter(sp);
		borderInsert.setTop(inputBox);
		borderInsert.setBottom(coinBox);
		
		Scene insertCoin = new Scene(borderInsert, 500, 300);
		return insertCoin;
	}
	/**
	* createBuyWindow() creates the Buy scene
	* @returns Scene
	*/
	public Scene createBuyWindow() 
	{
		String temp = "";
		char ch = 'A';
		
		for(Item p : machine.getProductTypes())
		{
			temp += ch + ") " + p.toString() + "\n";
			ch += 1;
		}
		
		Text coinsText = new Text(temp);
		coinsText.setFont(new Font(16));
		
		ScrollPane sp = new ScrollPane();
		sp.setContent(coinsText);
		
		HBox box = new HBox();
		Button buyButton = new Button("Buy");
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(buyButton, backButton);
		
		HBox inputBox = new HBox();
		Label inputLabel = new Label("Which Product: ");
		TextField choice = new TextField();
		inputBox.getChildren().addAll(inputLabel, choice);
		inputBox.setAlignment(Pos.CENTER);
		
		buyButton.setOnAction(e -> {
			if(choice.getText().length() == 1)
			{
				int index = choice.getText().toUpperCase().charAt(0) - 'A';
				if(index >= 0 && index < machine.getProductTypes().length)
				{
					try
					{
						machine.buyProduct(machine.getProductTypes()[index]);
						choice.setText("");
						messageWindow("Product bought.", createBuyWindow());
					}
					catch (VendingException ex)
					{
						messageWindow(ex.getMessage(), createBuyWindow());
					}
				}
			}
		});
		
		BorderPane borderInsert = new BorderPane();
		borderInsert.setTop(inputBox);
		borderInsert.setCenter(sp);
		borderInsert.setBottom(box);
		
		Scene buyProduct = new Scene(borderInsert, 500, 300);
		return buyProduct;
	}
	/**
	* createAddProductWindow() creates the addProduct Scene
	* @returns Scene
	*/
	public Scene createAddProductWindow()
	{
		// Add product menu --- text fields for different information about product
		//Scene addProduct; 3 labels 3 text boxes
		//return addProduct;
		Label firstLabel = new Label("Enter product name: ");
		Label secondLabel = new Label("Enter price of product: ");
		Label thirdLabel = new Label("Enter quantity: ");
		
		TextField name = new TextField();
		TextField price = new TextField();
		TextField quantity = new TextField();
		
		HBox addProductBox = new HBox();
		addProductBox.setAlignment(Pos.CENTER);
		Button addButton = new Button("Add product");
		Button backAdminButton = new Button("Back");
		addProductBox.getChildren().addAll(addButton,backAdminButton);
		
		GridPane grid = new GridPane();
		grid.add(firstLabel,0,0);
		grid.add(secondLabel,0,1);
		grid.add(thirdLabel,0,2);
		grid.add(name,1,0);
		grid.add(price,1,1);
		grid.add(quantity,1,2);
		grid.setAlignment(Pos.CENTER);
		
		backAdminButton.setOnAction(e -> primaryStage.setScene(createAdminMenu()));
		addButton.setOnAction(e -> {
			if(name.getText().length() > 0 && price.getText().length() > 0 && quantity.getText().length() > 0)
			{
				machine.addProduct(new Product(name.getText(),Double.parseDouble(price.getText())), Integer.parseInt(quantity.getText()));
				name.setText("");
				price.setText("");
				quantity.setText("");
				messageWindow("Product added.", createAddProductWindow());
			}
		});
		
		BorderPane borderAddProduct = new BorderPane();
		borderAddProduct.setCenter(grid);
		borderAddProduct.setBottom(addProductBox);

		Scene addProducts = new Scene(borderAddProduct, 500, 300);
		return addProducts;
	}
	/**
	* createRemoveCoinsWindow() creates the removeCoin Scene
	* @returns Scene
	*/
	public Scene createRemoveCoinsWindow()
	{
		String temp = "Removed: " + machine.removeMoney();
		Text rCoinsText = new Text(temp);
		rCoinsText.setFont(new Font(16));
		
		ScrollPane sp = new ScrollPane();
		sp.setContent(rCoinsText);
		
		HBox removeCoinsBox = new HBox();
		removeCoinsBox.setAlignment(Pos.CENTER);
		Button okButton = new Button("OK");
		removeCoinsBox.getChildren().add(okButton);
		
		okButton.setOnAction(e -> primaryStage.setScene(createAdminMenu()));
		
		BorderPane borderCoinsRemove = new BorderPane();
		borderCoinsRemove.setCenter(sp);
		borderCoinsRemove.setBottom(removeCoinsBox);
		
		Scene removeCoins = new Scene(borderCoinsRemove, 500, 300);
		return removeCoins;
	}
	/**
	* messageWindow() displays a message to the user
	* @param msg - string message
	* @param scene - is the scene its displaying to
	*/
	public void messageWindow(String msg, Scene scene)
	{
		HBox buttonBox = new HBox();
		Button okButton = new Button("OK");
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().add(okButton);
		
		Text msgText = new Text(msg);
		
		BorderPane msgPane = new BorderPane();
		msgPane.setCenter(msgText);
		msgPane.setBottom(buttonBox);
		
		okButton.setOnAction(e -> primaryStage.setScene(scene));
		
		Scene s = new Scene(msgPane, 500, 300);
		primaryStage.setScene(s);
	}
}