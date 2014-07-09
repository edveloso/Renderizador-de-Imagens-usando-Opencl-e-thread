/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Lavinia
 */
public class JGeraGraficos {

    public JGeraGraficos() {
    }

    public JGeraGraficos(ArrayList<DadosEstatisticos> listaJava, ArrayList<DadosEstatisticos> listaJavaThRead, ArrayList<DadosEstatisticos> listaOpenCl) {
        super();
        PrintGrafico(listaJava, listaJavaThRead, listaOpenCl);
    }

    private static DefaultTableXYDataset createDataset(ArrayList<DadosEstatisticos> listaJava, ArrayList<DadosEstatisticos> listaJavaThRead, ArrayList<DadosEstatisticos> listaOpenCl) {
        DefaultTableXYDataset dataset = new DefaultTableXYDataset();

        XYSeries serieJava = new XYSeries(" Java ", true, false);
        int i = 0;

        for (DadosEstatisticos dadosJava : listaJava) {
            i = i + 2;
            serieJava.add(i, dadosJava.getTempoGasto());
        }
        dataset.addSeries(serieJava);

        XYSeries serieThRead = new XYSeries(" Java ThRead ", true, false);
        i = 0;

        for (DadosEstatisticos dadosThRead : listaJavaThRead) {
            i = i + 2;
            serieThRead.add(i, dadosThRead.getTempoGasto());
        }
        dataset.addSeries(serieThRead);

        XYSeries serieOpenCL = new XYSeries(" OpenCL ", true, false);
        i = 0;

        for (DadosEstatisticos dadosOpenCl : listaOpenCl) {
            i = i + 2;
            serieOpenCL.add(i, dadosOpenCl.getTempoGasto());
        }
        dataset.addSeries(serieOpenCL);
        return dataset;
    }

    private static JFreeChart createGraficoXY(String title, String categoryAxisLabel, String valueAxisLabel, IntervalXYDataset dataset) {

        NumberAxis domainAxis = new NumberAxis(categoryAxisLabel);
        domainAxis.setAutoRangeIncludesZero(false);
        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        XYBarRenderer renderer = new ClusteredXYBarRenderer();

        XYPlot plot = new XYPlot((XYDataset) dataset, domainAxis, valueAxis, renderer);

        plot.setOrientation(PlotOrientation.VERTICAL);

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        return chart;
    }

    private void PrintGrafico(ArrayList<DadosEstatisticos> listaJava, ArrayList<DadosEstatisticos> listaJavaThRead, ArrayList<DadosEstatisticos> listaOpenCl) {
        int i = 0;
        String titulo = "";
        for (DadosEstatisticos dados : listaJava) {
            i++;
            if (listaJava.indexOf(dados) < 2) {
                titulo = titulo + "C" + i + " - " + dados.getDescricao() + "       ";
            } else {
                titulo = titulo + "C" + i + " - " + dados.getDimensoes() + "       ";
            }
        }

        JFreeChart chart = createGraficoXY("GRAFICO TOTAL DE PROCESSOS", titulo, " TEMPO (ms)", createDataset(listaJava, listaJavaThRead, listaOpenCl));
        ChartFrame frame = new ChartFrame("GRAFICO TOTAL DE PROCESSOS", chart);

        frame.setExtendedState(ChartFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    private String getValorArquivoTxt(String dir) {
        File arquivo = new File(dir);
        if (arquivo.exists()) {
            try (FileReader fr = new FileReader(arquivo)) {
                int c = fr.read();
                String valor = "";
                while (c != -1) {
                    valor = valor + (char) c;
                    c = fr.read();
                }
                return valor;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    public void PrintGraficoSegundoExemplo() {
        String valorJava = getValorArquivoTxt("c:\\AppsPrjFinal\\ESTOUROJVMJAVA.txt");
        String valorJavaThRead = getValorArquivoTxt("c:\\AppsPrjFinal\\ESTOUROJVMJAVATHREAD.txt");
        String valorJOCL = getValorArquivoTxt("c:\\AppsPrjFinal\\ESTOUROJVMJOCL.txt");
        if (valorJava.isEmpty()) {
            JOptionPane.showMessageDialog(new JPanel(), "Processo Java Não Executado", "Executar Processo", JOptionPane.INFORMATION_MESSAGE);
        } else if (valorJavaThRead.isEmpty()) {
            JOptionPane.showMessageDialog(new JPanel(), "Processo Java ThRead Não Executado", "Executar Processo", JOptionPane.INFORMATION_MESSAGE);
        } else if (valorJOCL.isEmpty()) {
            JOptionPane.showMessageDialog(new JPanel(), "Processo JOCL Não Executado", "Executar Processo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.clear();
            dataset.addValue(Float.parseFloat(valorJava), "Java", "Java");
            dataset.addValue(Float.parseFloat(valorJavaThRead), "Java ThRead", "Java ThRead");
            dataset.addValue(Float.parseFloat(valorJOCL), "JOCL", "JOCL");
            JFreeChart chart = ChartFactory.createBarChart("Grafio de Performance", null, "Tempo(ms)", dataset, PlotOrientation.VERTICAL, true, true, true);
            ChartFrame frame = new ChartFrame("Grafio de Performance", chart);

            frame.setBounds(300, 78, 800, 620);
            frame.setVisible(true);
        }
    }

    public ChartPanel GetGraficoParcial(ArrayList<DadosEstatisticos> listaParcial, String Titulo) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 0;
        dataset.clear();
        for (DadosEstatisticos dados : listaParcial) {
            i++;
            if (listaParcial.indexOf(dados) < 2) {
                dataset.setValue(dados.getTempoGasto(), "C" + i + " - " + dados.getDescricao(), "C" + i);
            } else {
                dataset.setValue(dados.getTempoGasto(), "C" + i + " - " + dados.getDimensoes(), "C" + i);
            }
        }

        JFreeChart chart = ChartFactory.createBarChart(Titulo, null, "Tempo(ms)", dataset, PlotOrientation.HORIZONTAL, true, true, true);

        ChartPanel CP = new ChartPanel(chart);
        CP.setBounds(5, 15, 180, 470);

        return CP;
    }
}
