package de.uka.ilkd.key.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import de.uka.ilkd.key.gui.utilities.GuiUtilities;
import de.uka.ilkd.key.java.Services;
import de.uka.ilkd.key.speclang.Contract;


public class ContractConfigurator extends JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 4002043118399402599L;
    private ContractSelectionPanel contractPanel;
    private JButton okButton;
    private JButton cancelButton;

    private boolean successful = false;


    // -------------------------------------------------------------------------
    // constructors
    // -------------------------------------------------------------------------

    public ContractConfigurator(JDialog owner, Services services, Contract[] contracts,
            String title, boolean allowMultipleContracts) {
        super(owner, "Contract Configurator", true);
        init(services, contracts, title, allowMultipleContracts);
    }


    public ContractConfigurator(Frame owner, Services services, Contract[] contracts, String title,
            boolean allowMultipleContracts) {
        super(owner, "Contract Configurator", true);
        init(services, contracts, title, allowMultipleContracts);
    }


    // -------------------------------------------------------------------------
    // internal methods
    // -------------------------------------------------------------------------

    /**
     * Helper for constructors.
     */
    private void init(Services services, Contract[] contracts, String title,
            boolean allowMultipleContracts) {
        // create contract panel
        contractPanel = new ContractSelectionPanel(services, allowMultipleContracts);
        contractPanel.setContracts(contracts, title);
        contractPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    okButton.doClick();
                }
            }
        });
        contractPanel.setMinimumSize(new Dimension(800, 500));
        getContentPane().add(contractPanel);

        // create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        Dimension buttonDim = new Dimension(100, 27);
        buttonPanel
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) buttonDim.getHeight() + 10));
        getContentPane().add(buttonPanel);

        // create "ok" button
        okButton = new JButton("OK");
        okButton.setPreferredSize(buttonDim);
        okButton.setMinimumSize(buttonDim);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                successful = true;
                setVisible(false);
                dispose();
            }
        });
        buttonPanel.add(okButton);
        getRootPane().setDefaultButton(okButton);

        // create "cancel" button
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(buttonDim);
        cancelButton.setMinimumSize(buttonDim);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                successful = false;
                setVisible(false);
                dispose();
            }
        });
        buttonPanel.add(cancelButton);
        GuiUtilities.attachClickOnEscListener(cancelButton);


        // show
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }



    // -------------------------------------------------------------------------
    // public interface
    // -------------------------------------------------------------------------

    /**
     * Tells whether the user clicked "ok".
     */
    public boolean wasSuccessful() {
        return successful;
    }


    /**
     * Returns the selected contract.
     */
    public Contract getContract() {
        return contractPanel.getContract();
    }
}
