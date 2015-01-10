package projecto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUI implements ActionListener{
    JFrame window = new JFrame();
    JPanel panel = new JPanel();
    JLabel textWorldSize = new JLabel("Tamanho do Mundo");
    JLabel textNAgents = new JLabel("Número de Agentes");
    JLabel textNObjects = new JLabel("Número de Objectos");
    JLabel textAgentType = new JLabel("Tipo do Agente");
    JLabel textLifeSpan = new JLabel("Tempo de vida");
    JLabel textFieldOfSight = new JLabel("Raio de visão");
    JLabel textAgentColor = new JLabel("Cor do Agente");
    JTextField tFieldWorldSize = new JTextField(10);
    JTextField tFieldFieldOfSight = new JTextField(10);
    JTextField tFieldLifeSpan = new JTextField(10);
    JTextField tFieldNAgents = new JTextField(10);
    JTextField tFieldNObjects = new JTextField(10);
    ButtonGroup optionAgentType = new ButtonGroup();
    JRadioButton optionRandom = new JRadioButton("Random", true);
    JRadioButton optionClosest = new JRadioButton("Mais próximo");
    JRadioButton optionMaxDiff = new JRadioButton("Máxima diferença");
    private JComboBox colors = new JComboBox();
    JButton button = new JButton("Button");
    
    //variaveis para retirar
    GUI agentInfo;
    static boolean gotInput;
    static int type;
    static int worldSize;
    static int nAgents;
    static int nObjects;
    static int agentType;
    static int lifeSpan;
    static int fieldOfSight;
    static String color;
    
    //Constructor
    GUI(int type){
        this.type = type;
        window.setLayout(new GridLayout(1, 2));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(type == 0){
            window.setTitle("Mundo");
            window.setSize(300, 175);
            panel.setSize(300, 175);
            panel.add(textWorldSize);
            panel.add(tFieldWorldSize);
            panel.add(textNAgents);
            panel.add(tFieldNAgents);
            panel.add(textNObjects);
            panel.add(tFieldNObjects);
        } else {
            window.setTitle("Agente");
            window.setSize(500, 125);
            panel.setSize(500, 175);
            panel.add(textAgentType);
            optionAgentType.add(optionRandom);
            optionAgentType.add(optionClosest);
            optionAgentType.add(optionMaxDiff);
            panel.add(optionRandom);
            panel.add(optionClosest);
            panel.add(optionMaxDiff);
            panel.add(textLifeSpan);
            panel.add(tFieldLifeSpan);
            panel.add(textFieldOfSight);
            panel.add(tFieldFieldOfSight);
            panel.add(textAgentColor);
            colors.addItem("Azul");
            colors.addItem("Amarelo");
            colors.addItem("Verde");
            colors.addItem("Vermelho");
            panel.add(colors);
        }
        
        panel.add(button);
        window.add(panel);
        window.setVisible(true);
        
        button.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        
        int flag = 0;
        String warning = "ERRO: \n";
        
        if(type == 0){
            if(!isInteger(tFieldWorldSize.getText())){
                flag = 1;
                warning += "O tamanho do mundo deve ser um inteiro\n";
            } else {
                if(Integer.parseInt(tFieldWorldSize.getText()) <= 0){
                    flag = 1;
                    warning += "O tamanho do mundo deve ser maior que 0\n";
                }
            }

            if(!isInteger(tFieldNAgents.getText())){
                flag = 1;
                warning += "O número de agentes deve ser um inteiro\n";
            } else {
                if(Integer.parseInt(tFieldNAgents.getText()) <= 0){
                    flag = 1;
                    warning += "O número de agentes deve ser maior que 0\n";
                }
            }

            if(!isInteger(tFieldNObjects.getText())){
                flag = 1;
                warning += "O número de objectos deve ser um inteiro\n";
            } else {
                if(Integer.parseInt(tFieldNObjects.getText()) <= 0){
                    flag = 1;
                    warning += "O número de objectos deve ser maior que 0\n";
                }
            }

            if(flag == 0){
                if(( Integer.parseInt(tFieldNObjects.getText()) + Integer.parseInt(tFieldNAgents.getText()) ) >= ( Integer.parseInt(tFieldWorldSize.getText()) * Integer.parseInt(tFieldWorldSize.getText()) )){
                    flag = 1;
                    warning += "O número de entidades não pode ser maior que o tamanho do mundo\n";
                }
            }

            if(flag == 1){
                JOptionPane.showMessageDialog(window, warning, "Erro", JOptionPane.WARNING_MESSAGE);
            } else {
                worldSize = Integer.parseInt(tFieldWorldSize.getText());
                nAgents = Integer.parseInt(tFieldNAgents.getText());
                nObjects = Integer.parseInt(tFieldNObjects.getText());
                window.dispose();
                agentCreation();
            }
        } else {
            
            //Submenus
            if(!isInteger(tFieldLifeSpan.getText())){
                flag = 1;
                warning += "O tempo de vida dever ser um inteiro\n";
            } else {
                if(Integer.parseInt(tFieldLifeSpan.getText()) <= 0){
                    flag = 1;
                    warning += "O tempo de vida deve ser maior que 0\n";
                }
            }
            
            if(!isInteger(tFieldFieldOfSight.getText())){
                flag = 1;
                warning += "O raio de visão deve ser um inteiro\n";
            } else {
                if(Integer.parseInt(tFieldFieldOfSight.getText()) <= 0){
                    flag = 1;
                    warning += "O raio de visão deve ser maior que 0\n";
                }
            }
            
            if(flag == 1){
                JOptionPane.showMessageDialog(window, warning, "Erro", JOptionPane.WARNING_MESSAGE);
            } else {
                if(optionRandom.isSelected()){
                    agentType = 1;
                } else if(optionClosest.isSelected()) {
                    agentType = 2;
                } else if(optionMaxDiff.isSelected()){
                    agentType = 3;
                }
                
                lifeSpan = Integer.parseInt(tFieldLifeSpan.getText());
                fieldOfSight = Integer.parseInt(tFieldFieldOfSight.getText());
                
                color = (String)colors.getSelectedItem();
                window.dispose();
                System.out.println(agentInfo.color + " " + agentInfo.agentType + " " + agentInfo.lifeSpan);
            }
            
        }
    }
    
    private void agentCreation(){
        for(int i = 0; i < GUI.nAgents; i++){
            agentInfo = new GUI(1);
        }
    }
    
    private boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }
}
