package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.entities.Pessoa;

public class ViewController implements Initializable {

	@FXML
	private ComboBox<Pessoa> comboBoxPessoa;

	// Já associa ao combo da tela
	private ObservableList<Pessoa> obsLists;

	@FXML
	private Button btTodas;
	
	public void onBtTodasAction() {
		//imprimindo todas as pessoas
		for(Pessoa pessoa : comboBoxPessoa.getItems()) {
			System.out.println(pessoa);
		}
	}
	
	@FXML
	public void onComboBoxPessoaAction() {
		// pega o item que foi selecionado no combo
		Pessoa pessoa = comboBoxPessoa.getSelectionModel().getSelectedItem();
		System.out.println(pessoa);
	}
	
	// Primeiro método que é chamado no programa - Quando é criado o controlador
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		List<Pessoa> lista = new ArrayList<>();
		lista.add(new Pessoa(1, "Pedro", "pedro@gmail.com"));
		lista.add(new Pessoa(2, "Joao", "joao@gmail.com"));
		lista.add(new Pessoa(3, "Ana", "ana@gmail.com"));

		obsLists = FXCollections.observableArrayList(lista);

		// o comboBox por padrão usa o toString da classe para exibir os valores
		comboBoxPessoa.setItems(obsLists);

		// metodo para exibir somente o nome da pessoa no combo
		Callback<ListView<Pessoa>, ListCell<Pessoa>> factory = lv -> new ListCell<Pessoa>() {
			@Override
			protected void updateItem(Pessoa item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		comboBoxPessoa.setCellFactory(factory);
		comboBoxPessoa.setButtonCell(factory.call(null));

	}
}
