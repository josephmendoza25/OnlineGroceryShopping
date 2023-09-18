// Authors: Soliyana Gebremedhin and Joseph Mendoza

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class ShoppingCart {

    JFrame frame;
    public JTextField txtItemName;
    public JTextField txtDairyType;
    public JTextField txtItemPrice;
    public JTextField txtBestBy;
    public JTextField txtReturnDate;
    public JTextField txtProductNameRemove;
    public JTable tblItems;
    static List<Product> productsList = new ArrayList<Product>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShoppingCart window = new ShoppingCart();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }//main

    /**
     * Create the application.
     */
    public ShoppingCart() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        LoadFileContent();
        frame = new JFrame();
        frame.setBounds(100, 100, 794, 748);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtItemName = new JTextField();
        txtItemName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtItemName.setBounds(127, 69, 184, 32);
        frame.getContentPane().add(txtItemName);
        txtItemName.setColumns(10);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(35, 75, 82, 21);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblDairyType = new JLabel("Product Type");
        lblDairyType.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDairyType.setBounds(35, 127, 82, 21);
        frame.getContentPane().add(lblDairyType);

        txtDairyType = new JTextField();
        txtDairyType.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDairyType.setColumns(10);
        txtDairyType.setBounds(127, 121, 184, 32);
        frame.getContentPane().add(txtDairyType);

        JLabel lblNewLabel_1_1 = new JLabel("Price");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_1.setBounds(35, 183, 82, 21);
        frame.getContentPane().add(lblNewLabel_1_1);

        txtItemPrice = new JTextField();
        txtItemPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtItemPrice.setColumns(10);
        txtItemPrice.setBounds(127, 177, 184, 32);
        frame.getContentPane().add(txtItemPrice);

        JLabel lblNewLabel_1_2 = new JLabel("Best By");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2.setBounds(35, 241, 82, 21);
        frame.getContentPane().add(lblNewLabel_1_2);

        txtBestBy = new JTextField();
        txtBestBy.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtBestBy.setColumns(10);
        txtBestBy.setBounds(127, 235, 184, 32);
        frame.getContentPane().add(txtBestBy);

        JLabel lblNewLabel_1_2_1 = new JLabel("Return Date");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1_2_1.setBounds(35, 294, 82, 21);
        frame.getContentPane().add(lblNewLabel_1_2_1);

        txtReturnDate = new JTextField();
        txtReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtReturnDate.setColumns(10);
        txtReturnDate.setBounds(127, 288, 184, 32);
        frame.getContentPane().add(txtReturnDate);

        JLabel lblNewLabel_2 = new JLabel("Add Item");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(110, 21, 93, 34);
        frame.getContentPane().add(lblNewLabel_2);

        JButton btnAddItem = new JButton("Add Item");
        btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddOption();
            }
        });
        btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAddItem.setBounds(110, 342, 121, 41);
        frame.getContentPane().add(btnAddItem);

        JLabel lblNewLabel_2_1 = new JLabel("Remove Item");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2_1.setBounds(506, 21, 163, 34);
        frame.getContentPane().add(lblNewLabel_2_1);

        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(447, 75, 82, 21);
        frame.getContentPane().add(lblNewLabel_1);

        txtProductNameRemove = new JTextField();
        txtProductNameRemove.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtProductNameRemove.setColumns(10);
        txtProductNameRemove.setBounds(539, 69, 184, 32);
        frame.getContentPane().add(txtProductNameRemove);

        JButton btnRemoveItem = new JButton("Remove Item");
        btnRemoveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RemoveOption();
            }
        });
        btnRemoveItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRemoveItem.setBounds(521, 128, 121, 41);
        frame.getContentPane().add(btnRemoveItem);

        tblItems = new JTable();
        tblItems.setBounds(47, 411, 676, 255);
        frame.getContentPane().add(tblItems);
        Display();

        JButton btnSortItemsByPrice = new JButton("Sort Items By Price");
        btnSortItemsByPrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SortByPrice();
            }
        });
        btnSortItemsByPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSortItemsByPrice.setBounds(521, 360, 204, 41);
        frame.getContentPane().add(btnSortItemsByPrice);

        JButton btnSortItems = new JButton("Sort Items");
        btnSortItems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SortByName();
            }
        });
        btnSortItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSortItems.setBounds(376, 360, 121, 41);
        frame.getContentPane().add(btnSortItems);

        JButton btnMakeReciept = new JButton("Make Reciept");
        btnMakeReciept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MakeReciept();
            }
        });
        btnMakeReciept.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMakeReciept.setBounds(477, 184, 204, 41);
        frame.getContentPane().add(btnMakeReciept);
    }//initialize

    // Load File Content Method
    public static void LoadFileContent() {
        Scanner fin = null;
        try {
            fin = new Scanner(new File("Items.csv"));
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(0);
        }
        int count = 0;
        while (fin.hasNext()) {
            String r = fin.nextLine();
            String[] A = r.split(",");// A[0] = name, A[1] = code, etc
            Product product = new Product(A[0], A[1], A[2], A[3], A[4]);
            productsList.add(product);
            count++;
        }

    }

    // Store File Content Method
    public static void StoreFileContent() {
        try (FileOutputStream file = new FileOutputStream("Items.csv");
             OutputStreamWriter out = new OutputStreamWriter(file, "UTF-8");
             BufferedWriter buf = new BufferedWriter(out);
             PrintWriter writer = new PrintWriter(buf)) {

            for (int i = 0; i < productsList.size(); i++) {
                writer.println(productsList.get(i).toCommaSeparatedString());
            }
        } catch (Exception ex) {

        }
    }

    // Display
    public void Display() {
        String[] columns = {"Name", "Dairy Type", "Price", "Best By", "Return Date"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (int i = 0; i < productsList.size(); i++) {
            Object[] colValues = {productsList.get(i).getItemName(), productsList.get(i).getDairyType(),
                    productsList.get(i).getPrice(), productsList.get(i).getBestBy(),
                    productsList.get(i).getReturnDate()};
            tableModel.addRow(colValues);
        }
        tblItems.setModel(tableModel);
    }

    // Add New Option
    public void AddOption() {
        Product product = new Product(txtItemName.getText(), txtDairyType.getText(), txtItemPrice.getText(),
                txtBestBy.getText(), txtReturnDate.getText());

        productsList.add(product);
        JOptionPane.showMessageDialog(frame, "Item Added");
        txtItemName.setText("");
        txtDairyType.setText("");
        txtBestBy.setText("");
        txtReturnDate.setText("");
        Display();
        StoreFileContent();
    }

    // Remove Option
    public void RemoveOption() {
        String name = txtProductNameRemove.getText();

        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getItemName().equalsIgnoreCase(name)) {
                productsList.remove(i);
            }
        }
        JOptionPane.showMessageDialog(frame, "Item Removed");
        txtProductNameRemove.setText("");
        Display();
        StoreFileContent();
    }

    // Sort by Name
    public void SortByName() {
        Collections.sort(productsList, new Comparator<Product>() {
            @Override
            public int compare(final Product object1, final Product object2) {
                return object1.getItemName().compareTo(object2.getItemName());
            }
        });
        Display();
    }

    // Sort by Price
    public void SortByPrice() {
        Collections.sort(productsList, new Comparator<Product>() {
            @Override
            public int compare(final Product object1, final Product object2) {
                return Double.compare(object1.getPriceValue(), object2.getPriceValue());
            }
        });
        Display();
    }

    // Make Reciept
    public void MakeReciept() {
        String recieptText = "";
        double total = 0;
        for (int i = 0; i < productsList.size(); i++) {
            recieptText += productsList.get(i).getItemName() + " - " + " 1 " + productsList.get(i).getPrice() + "\n";
            total = total + productsList.get(i).getPriceValue();
        }
        recieptText += "Total:$ " + total;

        JOptionPane.showMessageDialog(frame, recieptText);

    }

    public static class Product {

        private final String ItemName;
        private final String DairyType;
        private final String Price;
        private final String BestBy;
        private final String ReturnDate;

        public Product(String _itemName, String _dairyType, String _price, String _bestBy, String _returnDate) {
            this.ItemName = _itemName;
            this.DairyType = _dairyType;
            this.Price = _price;
            this.BestBy = _bestBy;
            this.ReturnDate = _returnDate;
        }

        @Override
        public String toString() {
            return "| " + ItemName + " - " + DairyType + " - " + Price + " - " + BestBy + " - " + ReturnDate + " |";
        }

        public String toCommaSeparatedString() {
            return ItemName + "," + DairyType + "," + Price + "," + BestBy + "," + ReturnDate;
        }

        public String getItemName() {
            return ItemName;
        }

        public String getPrice() {
            return Price;
        }

        public double getPriceValue() {
            return Double.parseDouble(Price.substring(1));
        }

        public String getDairyType() {
            return DairyType;
        }

        public String getBestBy() {
            return BestBy;
        }

        public String getReturnDate() {
            return ReturnDate;
        }

    }//product

}//Shopping cart