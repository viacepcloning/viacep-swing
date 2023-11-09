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

import javax.swing.*;

/**
 * @author marco.mangan@pucrs.br
 */
public class ViaCepApp {

    private static JTextField addTextField(JPanel p, String label) {
        JLabel l = new JLabel(label, JLabel.TRAILING);
        p.add(l);
        JTextField textField = new JTextField(25);
        l.setLabelFor(textField);
        textField.setEditable(false);
        textField.setEnabled(false);
        p.add(textField);
        return textField;
    }

    private static void createAndShowGUI() {

        String[] labels = {"CEP: ", "Logradouro: ", "Complemento: ", "Bairro: ", "Localidade: ", "UF: "};
        int numPairs = labels.length;

        Cep cep = ViaCepClient.findCep("01001000");

        JPanel p = new JPanel(new SpringLayout());

        for (String label : labels) {
            addTextField(p, label);
        }

        SpringUtilities.makeCompactGrid(p,
                numPairs, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        JFrame frame = new JFrame("Via CEP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p.setOpaque(true);  //content panes must be opaque
        frame.setContentPane(p);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(ViaCepApp::createAndShowGUI);
    }
}