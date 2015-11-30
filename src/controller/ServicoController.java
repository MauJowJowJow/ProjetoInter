package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.util.converter.NumberStringConverter;
import model.Item_servico;
import model.Pessoa;
import model.Produto;
import model.Quarto;
import util.Alerta;
import view.ConsultaPessoaView;
import view.ConsultaQuartoView;

public class ServicoController extends ControllerDefault {
	private Pessoa pessoa = new Pessoa();
	private Quarto quarto = new Quarto();
	private Produto produto = new Produto();

	private boolean faturamentoValido;
	private boolean item_servicoValido;

	@FXML
	private TextField txtCodigo;

	@FXML
	private DatePicker txtDataEmissao;

	@FXML
	private TextField txtCodigoReserva;

	@FXML
	private TextField txtCodigoPessoa;

	@FXML
	private Button btnPesquisaPessoa;

	@FXML
	private TextField txtNomePessoa;

	@FXML
	private TextField txtCodigoQuarto;

	@FXML
	private Button btnPesquisaQuarto;

	@FXML
	private TextField txtNomeQuarto;

	@FXML
	private TextField txtCodigoProduto;

	@FXML
	private Button btnPesquisaProduto;

	@FXML
	private TextField txtNomeProduto;

	@FXML
	private TextField txtQuantidade;

	@FXML
	private TextField txtValorUnitario;

	@FXML
	private TextField txtValorTotal;

	@FXML
	private Button btnAddLinha;

	@FXML
	private Button btnDelLinha;

	@FXML
	private TableView<Item_servico> tbvItem_servico;

	@FXML
	private TableColumn<Item_servico, Integer> tbcCodProduto;
	@FXML
	private TableColumn<Item_servico, String> tbcNomeProduto;
	@FXML
	private TableColumn<Item_servico, Integer> tbcQuantidade;
	@FXML
	private TableColumn<Item_servico, Double> tbcValorUnitario;
	@FXML
	private TableColumn<Item_servico, Double> tbcValorTotal;

	@FXML
	private TextField txtValorReserva;

	@FXML
	private TextField txtValorServico;

	@FXML
	private TextField txtValorFaturado;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCarregarServico;

	public boolean isServicoValido() {
		return faturamentoValido;
	}

	public void setServicoValido(boolean faturamentoValido) {
		this.faturamentoValido = faturamentoValido;
	}

	public boolean isItem_servicoValido() {
		return item_servicoValido;
	}

	public void setItem_servicoValido(boolean item_servicoValido) {
		this.item_servicoValido = item_servicoValido;
	}

	public void setPessoa(Pessoa pessoa) {
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(this.pessoa, pessoa);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setQuarto(Quarto quarto) {
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(this.quarto, quarto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setProduto(Produto produto) {
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(this.produto, produto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);

		txtDataEmissao.setValue(LocalDate.now());

		imagensBotoes();
		criaBindings();
		eventosPesquisa();
		eventosBotoes();
		eventosCampos();
		iniciaTableView();
	}

	private void criaBindings() {
		// Binda Pessoa
		txtCodigoPessoa.textProperty().bindBidirectional(getPessoa().getCodigoProperty(), new NumberStringConverter());
		txtNomePessoa.textProperty().bindBidirectional(getPessoa().getNomeProperty());

		// Binda Quarto
		txtCodigoQuarto.textProperty().bindBidirectional(getQuarto().getCodigoProperty(), new NumberStringConverter());
		txtNomeQuarto.textProperty().bindBidirectional(getQuarto().getNomeProperty());

		// Binda Produto
		txtCodigoProduto.textProperty().bindBidirectional(getProduto().getCodigoProperty(),
				new NumberStringConverter());
		txtNomeProduto.textProperty().bindBidirectional(getProduto().getDescProdutoProperty());
	}

	private void imagensBotoes() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		btnPesquisaPessoa.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
		btnPesquisaQuarto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
		btnPesquisaProduto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));

		btnAddLinha.setGraphic(new ImageView(classLoader.getResource("Plus.png").toString()));
		btnDelLinha.setGraphic(new ImageView(classLoader.getResource("minus.png").toString()));
	}

	private void eventosPesquisa() {
		btnPesquisaPessoa.setOnAction(evt -> {
			ConsultaPessoaView consultaPessoaView = new ConsultaPessoaView();

			try {
				consultaPessoaView.iniciaTela(getScene(), Modality.WINDOW_MODAL);

				ConsultaPessoaController controller = consultaPessoaView.getFxmlLoader()
						.<ConsultaPessoaController> getController();
				if (controller.getModel() != null) {
					setPessoa((Pessoa) controller.getModel());

					txtCodigoPessoa.setText(Integer.toString(getPessoa().getCodigo()));
					txtNomePessoa.setText(getPessoa().getNome());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		btnPesquisaQuarto.setOnAction(evt -> {
			ConsultaQuartoView consultaQuartoView = new ConsultaQuartoView();

			try {
				consultaQuartoView.iniciaTela(getScene(), Modality.WINDOW_MODAL);

				ConsultaQuartoController controller = consultaQuartoView.getFxmlLoader()
						.<ConsultaQuartoController> getController();
				if (controller.getModel() != null) {
					setQuarto((Quarto) controller.getModel());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		btnPesquisaProduto.setOnAction(evt -> {

		});
	}

	private void eventosBotoes() {
		btnAddLinha.setOnAction(evt -> {
			Item_servico item_servico = new Item_servico();

			if (txtCodigoProduto.getText().isEmpty())
				txtCodigoProduto.setText("0");

			// if(!validaItemReserva())
			// return;

			item_servico.getPK().setProduto(getProduto());

			if (txtQuantidade.getText().isEmpty())
				txtQuantidade.setText("0");

			item_servico.setQuantidadeVendido(Integer.parseInt(txtQuantidade.getText()));

			if (txtValorUnitario.getText().isEmpty())
				txtValorUnitario.setText("0");

			item_servico.setValorUnitario(Double.parseDouble(txtValorUnitario.getText()));

			if (txtValorTotal.getText().isEmpty())
				txtValorTotal.setText("0");

			item_servico.setValorTotal(Double.parseDouble(txtValorTotal.getText()));

			if (produtoJaExiste(item_servico)) {
				Alerta alerta = new Alerta(getStage().getTitle(), "Produto já foi inserido!");
				alerta.Erro(getStage());
			} else {
				tbvItem_servico.getItems().add(item_servico);
				setProduto(null);
			}
		});

		btnDelLinha.setOnAction(evt -> {
			ObservableList<Item_servico> list = tbvItem_servico.getSelectionModel().getSelectedItems();

			if (list.size() > 0) {
				Alerta alerta = new Alerta(getStage().getTitle(),
						"Deseja mesmo excluir este(s) produto(s) do serviço?");

				if (alerta.Confirm(getStage()))
					tbvItem_servico.getItems().removeAll(list);
			}
		});
	}

	private void eventosCampos() {
		txtCodigoPessoa.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if (newValue)
				return;

			setServicoValido(true);

			if (!validaPessoa()) {
				setItem_servicoValido(false);
			} else {
				Pessoa pessoa = getPessoa().exists();

				if (pessoa != null) {
					setPessoa(pessoa);
				} else {
					setServicoValido(false);
					Alerta alerta = new Alerta(getStage().getTitle(), getPessoa().getErrors());

					alerta.Erro(getStage());
				}
			}
		});

		txtCodigoQuarto.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if (newValue)
				return;

			setServicoValido(true);

			if (!validaQuarto()) {
				setServicoValido(false);
			} else {
				Quarto quarto = getQuarto().exists();

				if (quarto != null) {
					setQuarto(quarto);
				} else {
					setServicoValido(false);
					Alerta alerta = new Alerta(getStage().getTitle(), getQuarto().getErrors());

					alerta.Erro(getStage());
				}
			}
		});

		txtCodigoProduto.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if (newValue)
				return;

			setItem_servicoValido(true);

			if (!validaProduto()) {
				setItem_servicoValido(false);
			} else {
				Produto produto = getProduto().exists();

				if (produto != null) {
					setProduto(produto);

					if (txtValorUnitario.getText().isEmpty())
						txtValorUnitario.setText(getProduto().getValorProduto().toString());
				} else {
					setItem_servicoValido(false);
					Alerta alerta = new Alerta(getStage().getTitle(), getProduto().getErrors());

					alerta.Erro(getStage());
				}
			}
		});

		txtQuantidade.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if (newValue)
				return;

			if (txtQuantidade.getText().isEmpty())
				txtQuantidade.setText("0");

			if (txtValorUnitario.getText().isEmpty())
				txtValorUnitario.setText("0");

			txtValorTotal.setText(Double.toString(
					Integer.parseInt(txtQuantidade.getText()) * Double.parseDouble(txtValorUnitario.getText())));
		});
	}

	private void iniciaTableView() {
		tbcCodProduto.setCellValueFactory(c -> {
			if (c.getValue() != null) {
				return c.getValue().getPK().getProduto().getCodigoProperty().asObject();
			} else {
				return new SimpleIntegerProperty(0).asObject();
			}
		});

		tbcNomeProduto.setCellValueFactory(c -> {
			if (c.getValue() != null) {
				return c.getValue().getPK().getProduto().getDescProdutoProperty();
			} else {
				return new SimpleStringProperty("");
			}
		});

		tbcQuantidade.setCellValueFactory(new PropertyValueFactory<Item_servico, Integer>("quantidadeVendido"));
		tbcValorUnitario.setCellValueFactory(new PropertyValueFactory<Item_servico, Double>("valorUnitario"));
		tbcValorTotal.setCellValueFactory(new PropertyValueFactory<Item_servico, Double>("valorTotal"));
	}

	private boolean produtoJaExiste(Item_servico item_servico) {
		for (Item_servico item_servico2 : tbvItem_servico.getItems()) {
			if (item_servico2.getPK().getProduto().getCodigo() == item_servico.getPK().getProduto().getCodigo()
					&& item_servico2.getPK().getServico().getCodigo() == item_servico.getPK().getServico()
							.getCodigo())

				return true;
		}
		return false;
	}

	private void atualizaValorTotal() {
		if (txtValorReserva.getText().isEmpty())
			txtValorReserva.setText("0");

		if (txtValorServico.getText().isEmpty())
			txtValorServico.setText("0");

		txtValorFaturado.setText(Double.toString(
				Double.parseDouble(txtValorReserva.getText()) + Double.parseDouble(txtValorServico.getText())));
	}

	/* Validações especificas */
	private boolean validaPessoa() {
		if (txtCodigoPessoa.getText() == "0" || txtCodigoPessoa.getText().isEmpty()) {
			setServicoValido(false);

			Alerta alerta = new Alerta(getStage().getTitle(), "Pessoa não informada!");

			alerta.Erro(getStage());
		}
		return true;
	}

	private boolean validaQuarto() {
		if (txtCodigoQuarto.getText() == "0" || txtCodigoQuarto.getText().isEmpty()) {
			setServicoValido(false);

			Alerta alerta = new Alerta(getStage().getTitle(), "Quarto não informado!");

			alerta.Erro(getStage());
		}
		return true;
	}

	private boolean validaProduto() {
		if (txtCodigoProduto.getText() == "0" || txtCodigoProduto.getText().isEmpty()) {
			setItem_servicoValido(false);

			Alerta alerta = new Alerta(getStage().getTitle(), "Produto não informado!");

			alerta.Erro(getStage());
		}
		return true;
	}
}
