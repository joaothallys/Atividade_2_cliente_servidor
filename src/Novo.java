import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Novo {
    JPanel painel;
    private JRadioButton M;
    private JRadioButton F;
    public JTextField nome;
    public JTextField cpf;
    public JTextField nasc;
    public JTextField civil;
    public JTextField prof;
    public JButton botao2;
    private ButtonGroup sexoGroup;

    public Novo() {
        sexoGroup = new ButtonGroup();
        sexoGroup.add(M);
        sexoGroup.add(F);

        botao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nome.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo nome não pode ser vazio");
                    return;
                }

                String cpfTexto = cpf.getText();
                if (cpfTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo CPF não pode ser vazio");
                    return;
                }
                if (cpfTexto.equals("00000000000") ||
                        cpfTexto.equals("11111111111") ||
                        cpfTexto.equals("22222222222") ||
                        cpfTexto.equals("33333333333") ||
                        cpfTexto.equals("44444444444") ||
                        cpfTexto.equals("55555555555") ||
                        cpfTexto.equals("66666666666") ||
                        cpfTexto.equals("77777777777") ||
                        cpfTexto.equals("88888888888") ||
                        cpfTexto.equals("99999999999") ||
                        (cpfTexto.length() != 11)) {
                    JOptionPane.showMessageDialog(null, "CPF inválido");
                    return;
                }

                char dig10, dig11;
                int sm, i, r, num, peso;

                try {
                // calculo 1
                    sm = 0;
                    peso = 10;
                    for (i = 0; i < 9; i++) {
                        num = (int) (cpfTexto.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso - 1;
                    }

                    r = 11 - (sm % 11);
                    if ((r == 10) || (r == 11))
                        dig10 = '0';
                    else
                        dig10 = (char) (r + 48);

                    // calculo 2
                    sm = 0;
                    peso = 11;
                    for (i = 0; i < 10; i++) {
                        num = (int) (cpfTexto.charAt(i) - 48);
                        sm = sm + (num * peso);
                        peso = peso - 1;
                    }

                    r = 11 - (sm % 11);
                    if ((r == 10) || (r == 11))
                        dig11 = '0';
                    else
                        dig11 = (char) (r + 48);


                    if ((dig10 == cpfTexto.charAt(9)) && (dig11 == cpfTexto.charAt(10))) {

                    } else {
                        JOptionPane.showMessageDialog(null, "CPF inválido");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao validar o CPF");
                    return;
                }

                String dataNascTexto = nasc.getText();
                if (dataNascTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo data de nascimento não pode ser vazio");
                    return;
                }

                String estadoCivil = civil.getText();
                if (estadoCivil.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo estado civil não pode ser vazio");
                    return;
                }

                String profissao = prof.getText();
                if (profissao.isEmpty()) {
                    profissao = "Desempregado(a)";
                }


                LocalDate dataNascimento;
                try {
                    dataNascimento = LocalDate.parse(dataNascTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data de nascimento inválido. Use dd/MM/yyyy.");
                    return;
                }
                int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

                String sexo;
                if (M.isSelected()) {
                    sexo = "Masculino";
                } else if (F.isSelected()) {
                    sexo = "Feminino";
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um sexo.");
                    return;
                }

                String mensagem = "Nome: " + nome.getText() +
                        "\nIdade: " + idade +
                        "\nSexo: " + sexo +
                        "\nEstado Civil: " + estadoCivil +
                        "\nProfissão: " + profissao;

                if (profissao.equalsIgnoreCase("engenheiro") || profissao.equalsIgnoreCase("analista de sistemas")) {
                    mensagem += "\nVagas disponíveis na área.";
                }

                JOptionPane.showMessageDialog(null, mensagem);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Pessoas");
        frame.setContentPane(new Novo().painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
