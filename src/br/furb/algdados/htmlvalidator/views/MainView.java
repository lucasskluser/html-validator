/**
 * Trabalho I
 * Alunos Lucas Samuel e Matheus Boing
 * Disciplina de Algoritmos e Estruturas de Dados
 * Professor Gilvan Justino
 */
package br.furb.algdados.htmlvalidator.views;

import br.furb.algdados.htmlvalidator.controllers.HTMLController;
import br.furb.algdados.htmlvalidator.utils.list.ListaEncadeada;
import br.furb.algdados.htmlvalidator.utils.list.NoLista;
import br.furb.algdados.htmlvalidator.utils.tagfrequency.TagFrequency;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainView {
    private JTextField textFieldFile;
    private JButton buttonAnalyseFile;
    private JTextArea textAreaResult;
    private JLabel labelFile;
    private JTable tableTagsFrequency;
    private JScrollPane jtableScrollPane;
    private JPanel jPanel;

    private DefaultTableModel tableModel;
    private HTMLController controller;

    public MainView(HTMLController controller) {
        createListeners();
        this.controller = controller;
    }

    public JPanel getJPanel() {
        return this.jPanel;
    }

    /**
     * Exibe o JFrame
     */
    public void show() {
        JFrame frame = createJFrame();
        textFieldFile.setText(System.getProperty("user.dir"));
        frame.setVisible(true);
    }

    /**
     * Exibe uma mensagem de informação
     * @param message Mensagem a ser exibida
     */
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(jPanel, message, null, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe uma mensagem de erro
     * @param message Mensagem a ser exibida
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(jPanel, message, "Ops!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Carrega o resultado da validação na view e preenche
     * a tabela de tags com suas frequências
     * @param absolutePath Caminho absoluto do arquivo validado
     * @param tagsMap Mapa de tags com suas frequências
     */
    public void loadResult(String absolutePath, ListaEncadeada<TagFrequency> tagsMap) {
        loadResult(absolutePath, tagsMap, null);
    }

    /**
     * Carrega o resultado da validação na view e preenche
     * a tabela de tags com suas frequências
     * @param absolutePath Caminho absoluto do arquivo validado
     * @param tagsMap Mapa de tags com suas frequências
     * @param validationError Erro da validação
     */
    public void loadResult(String absolutePath, ListaEncadeada<TagFrequency> tagsMap, String validationError) {
        textFieldFile.setText(absolutePath);

        if (validationError != null) {
            textAreaResult.setText(String.format("Erro de formatação: %s", validationError));
        } else {
            textAreaResult.setText("O arquivo está bem formatado");
        }

        loadTagsInTable(tagsMap);
    }

    /**
     * Preenche a tabela de tags com suas frequências
     * @param tagsMap Mapa de tags com suas frequências
     */
    private void loadTagsInTable(ListaEncadeada<TagFrequency> tagsMap) {
        tableModel.setRowCount(0);
        NoLista<TagFrequency> noLista = tagsMap.getPrimeiro();
        ListaEncadeada<TagFrequency> listaInversa = new ListaEncadeada<>();

        while (noLista != null) {
            listaInversa.inserir(noLista.getInfo());
            noLista = noLista.getProximo();
        }

        noLista = listaInversa.getPrimeiro();

        while (noLista != null) {
            Object key = noLista.getInfo().getTag().equals("<html>") ? "<html>&lt;html&gt;</html>" : noLista.getInfo().getTag();
            tableModel.addRow(new Object[] { key, noLista.getInfo().getFrequency() });

            noLista = noLista.getProximo();
        }
    }

    /**
     * Instancia um novo JFrame
     * @return Novo JFrame
     */
    private JFrame createJFrame() {
        JFrame frame = new JFrame();
        frame.setContentPane(jPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Validador de HTML - Lucas Samuel & Matheus Boing");
        frame.setAutoRequestFocus(true);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);

        return frame;
    }

    /**
     * Instancia componentes customizados da view
     */
    private void createUIComponents() {
        Object[] columns = { "Tag", "Frequência" };
        tableModel = new DefaultTableModel(columns, 0);
        tableTagsFrequency = new JTable(tableModel);
        jtableScrollPane = new JScrollPane(tableTagsFrequency);
    }

    /**
     * Define os listeners de ação
     */
    private void createListeners() {
        buttonAnalyseFile.addActionListener(e -> buttonAnalyseFileClickListener());

        textFieldFile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    textFieldFileOnEnterPressListener();
                } catch (Exception ex) {
                    showError("Não foi possível processar o caminho do arquivo");
                }
            }
        });
    }

    private void buttonAnalyseFileClickListener() {
        if (textFieldFile.getText().length() > 0) {
            controller.selectFile(textFieldFile.getText());
        }
    }

    private void textFieldFileOnEnterPressListener() {
        buttonAnalyseFileClickListener();
    }
}
