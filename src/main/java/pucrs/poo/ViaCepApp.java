package pucrs.poo;

/*
 * Copyright (c) 2023, PUCRS and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * An application that uses SpringLayout to create a forms-type layout.
 * Other files required: SpringUtilities.java.
 */

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
import com.gtbr.utils.CEPUtils;
import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

import javax.swing.*;
import java.awt.*;

/**
 * @author marco.mangan@pucrs.br
 */
public class ViaCepApp {
    static {
        try {
             UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
// by including the https://github.com/material-ui-swing/DarkStackOverflowTheme
            // UIManager.setLookAndFeel(new MaterialLookAndFeel(new DarkStackOverflowTheme()));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private static JTextField addTextField(final JPanel p, final String label) {
        final JLabel l = new JLabel(label, JLabel.TRAILING);
        p.add(l);
        final JTextField textField = new JTextField(25);
        l.setLabelFor(textField);
        textField.setEditable(false);
        p.add(textField);
        return textField;
    }

    private static void createAndShowGUI() {


        final JPanel p = new JPanel(new SpringLayout());

        final JTextField cepField = addTextField(p, "CEP");
        final JTextField logradouroField = addTextField(p, "Logradouro");
        final JTextField complementoField = addTextField(p, "Complemento");
        final JTextField bairroField = addTextField(p, "Bairro");
        final JTextField localidadeField = addTextField(p, "Localidade");
        final JTextField ufField = addTextField(p, "UF");

        cepField.setEditable(true);

        SpringUtilities.makeCompactGrid(p,
                6, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        final JFrame frame = new JFrame("Via CEP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p.setOpaque(true);  //content panes must be opaque
        frame.setContentPane(p);

        frame.pack();
        frame.setVisible(true);

        cepField.addActionListener(e ->
        {
            cepField.setEditable(false);
            javax.swing.SwingUtilities.invokeLater(() ->
            {
                try {
                    String endereco = CEPUtils.removeMascaraCep(cepField.getText());
                    Cep cep = ViaCepClient.findCep(endereco);
                    cepField.setForeground(Color.BLACK);
                    cepField.setText(cep.getCep());
                    logradouroField.setText(cep.getLogradouro());
                    complementoField.setText(cep.getComplemento());
                    bairroField.setText(cep.getBairro());
                    localidadeField.setText(cep.getLocalidade());
                    ufField.setText(cep.getUf());
                } catch (Exception ex) {
                    cepField.setForeground(Color.RED);
                    logradouroField.setText("");
                    complementoField.setText("");
                    bairroField.setText("");
                    localidadeField.setText("");
                    ufField.setText("");
                } finally {
                    cepField.setEditable(true);
                }
            });

        });

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(ViaCepApp::createAndShowGUI);
    }
}