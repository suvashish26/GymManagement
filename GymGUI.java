import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

// This is the main class that runs the gym system and listens for button clicks
public class GymGUI implements ActionListener {
    private JFrame frame;
    // This list holds all the gym members we add
    private ArrayList<GymMember> memberList = new ArrayList<>();
    
    // These are the main panels for the title, main menu, and member forms
    private JPanel titlePanel, mainPanel, regularPanel, premiumPanel;
    
    // These are the buttons for the main menu to do different tasks
    private JButton btnAddRegular, btnAddPremium, btnActivate, btnDeactivate, 
                   btnMarkAttendance, btnRevertRegular, btnRevertPremium, btnDisplayRegular, 
                   btnDisplayPremium, btnSaveToFile, btnReadFromFile;
    
    // These are the text fields and radio buttons for the regular member form
    private JTextField regTxtId, regTxtName, regTxtLocation, regTxtPhone, 
                      regTxtEmail, regTxtReferral;
    private JRadioButton regRbMale, regRbFemale, regRbOther;
    private ButtonGroup regGenderGroup;
    private JComboBox<String> dobDayComboBox, dobMonthComboBox, dobYearComboBox;
    private JComboBox<String> msDayComboBox, msMonthComboBox, msYearComboBox;
    
    // These are the text fields and radio buttons for the premium member form
    private JTextField premTxtId, premTxtName, premTxtLocation, premTxtPhone, 
                       premTxtEmail, premTxtTrainer;
    private JRadioButton premRbMale, premRbFemale, premRbOther;
    private ButtonGroup premGenderGroup;
    private JComboBox<String> premDobDayComboBox, premDobMonthComboBox, premDobYearComboBox;
    private JComboBox<String> premMsDayComboBox, premMsMonthComboBox, premMsYearComboBox;
    
    // These are the buttons for the regular and premium forms
    private JButton regBtnClear, regBtnBack, regBtnSubmit;
    private JButton premBtnClear, premBtnBack, premBtnSubmit;

    private JTextField txtMemberIdInput;
    private JTextField txtPlanPrice;
    private JComboBox<String> planComboBox;
    private JTextField priceField;
    private JButton upgradePlanBtn, calculateDiscountBtn, payDueBtn;
    private JTextField paymentAmountField;
    private JTextField plans;
    // This sets up the main window and all the parts of the interface
    public GymGUI() {
        frame = new JFrame("Gym Membership System");
        // Setting the size of the window
        frame.setSize(900, 700);
        // Making the app close when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Using exact positions for everything
        frame.setLayout(null);

        titlePanel = new JPanel();
        // Setting up the layout for the title panel
        titlePanel.setLayout(null);
        // Making the background black
        titlePanel.setBackground(Color.BLACK);
        // Setting the size and position of the title panel
        titlePanel.setBounds(0, 0, 900, 80);
        
        JLabel titleLabel = new JLabel("GYM MEMBERSHIP", JLabel.CENTER);
        // Putting the title in the center
        titleLabel.setBounds(0, 0, 900, 80);
        // Making the title big and bold
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        // Setting the text color to white
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        frame.add(titlePanel);
            
        mainPanel = new JPanel();
        // Setting up the layout for the main panel
        mainPanel.setLayout(null);
        // Setting the size and position of the main panel
        mainPanel.setBounds(0, 80, 900, 620);
        // Setting a light gray background
        mainPanel.setBackground(new Color(245, 245, 245)); 
        frame.add(mainPanel);

        
        // Adding a label to ask for a member ID
        JLabel lblMemberId = new JLabel("Enter Member ID:");
        lblMemberId.setBounds(550, 50, 150, 25);
        mainPanel.add(lblMemberId);

        
        // Adding a text box for entering the member ID
        txtMemberIdInput = new JTextField();
        txtMemberIdInput.setBounds(550, 80, 200, 25);
        mainPanel.add(txtMemberIdInput);
        
        // Adding a button to activate a membership
        btnActivate = new JButton("Activate Membership");
        btnActivate.setBounds(550, 120, 200, 30);
        btnActivate.setBackground(new Color(76, 175, 80));
        btnActivate.setForeground(Color.WHITE);
        btnActivate.addActionListener(this);
        mainPanel.add(btnActivate);
        
        // Adding a button to deactivate a membership
        btnDeactivate = new JButton("Deactivate Membership");
        btnDeactivate.setBounds(550, 160, 200, 30);
        btnDeactivate.setBackground(new Color(211, 47, 47));
        btnDeactivate.setForeground(Color.WHITE);
        btnDeactivate.addActionListener(this);
        mainPanel.add(btnDeactivate);
        
        // Adding a button to add a regular member
        btnAddRegular = new JButton("Add Regular Member");
        btnAddRegular.setBounds(50, 50, 200, 40);
        btnAddRegular.setBackground(new Color(33, 150, 243)); 
        btnAddRegular.setForeground(Color.WHITE);   
        btnAddRegular.addActionListener(this);
        mainPanel.add(btnAddRegular);

        // Adding a button to add a premium member
        btnAddPremium = new JButton("Add Premium Member");
        btnAddPremium.setBounds(50, 110, 200, 40);
        btnAddPremium.setBackground(new Color(33, 150, 243)); 
        btnAddPremium.setForeground(Color.WHITE);
        btnAddPremium.addActionListener(this);
        mainPanel.add(btnAddPremium);

        // Adding a button to save member details to a file
        btnSaveToFile = new JButton("Save to File");
        btnSaveToFile.setBounds(50, 170, 200, 40);
        btnSaveToFile.setBackground(new Color(0, 150, 136));
        btnSaveToFile.setForeground(Color.WHITE);
        btnSaveToFile.addActionListener(this);
        mainPanel.add(btnSaveToFile);

        // Adding a button to read member details from a file
        btnReadFromFile = new JButton("Read from File");
        btnReadFromFile.setBounds(300, 170, 200, 40);
        btnReadFromFile.setBackground(new Color(0, 150, 136));
        btnReadFromFile.setForeground(Color.WHITE);
        btnReadFromFile.addActionListener(this);
        mainPanel.add(btnReadFromFile);

        // Adding a button to mark a member's attendance
        btnMarkAttendance = new JButton("Mark Attendance");
        btnMarkAttendance.setBounds(550, 200, 200, 30);
        btnMarkAttendance.setBackground(new Color(46, 125, 50)); 
        btnMarkAttendance.setForeground(Color.WHITE);
        btnMarkAttendance.addActionListener(this);
        mainPanel.add(btnMarkAttendance);

        // Adding a button to display regular members
        btnDisplayRegular = new JButton("Display Regular");
        btnDisplayRegular.setBounds(300, 50, 200, 40);
        btnDisplayRegular.setBackground(new Color(33, 150, 243)); 
        btnDisplayRegular.setForeground(Color.WHITE);
        btnDisplayRegular.addActionListener(this);
        mainPanel.add(btnDisplayRegular);

        // Adding a button to display premium members
        btnDisplayPremium = new JButton("Display Premium");
        btnDisplayPremium.setBounds(300, 110, 200, 40);
        btnDisplayPremium.setBackground(new Color(33, 150, 243)); 
        btnDisplayPremium.setForeground(Color.WHITE);
        btnDisplayPremium.addActionListener(this);
        mainPanel.add(btnDisplayPremium);
            
        // Adding a button to revert a regular member
        btnRevertRegular = new JButton("Revert Regular");
        btnRevertRegular.setBounds(550, 240, 200, 30);
        btnRevertRegular.setBackground(new Color(245, 124, 0)); 
        btnRevertRegular.setForeground(Color.WHITE);    
        btnRevertRegular.addActionListener(this);
        mainPanel.add(btnRevertRegular);

        // Adding a button to revert a premium member
        btnRevertPremium = new JButton("Revert Premium");
        btnRevertPremium.setBounds(550, 285, 200, 30);
        btnRevertPremium.setBackground(new Color(198, 40, 40)); 
        btnRevertPremium.setForeground(Color.WHITE);
        btnRevertPremium.addActionListener(this);
        mainPanel.add(btnRevertPremium);

        // Adding a label to choose an upgrade plan
        JLabel upgradeLabel = new JLabel("Upgrade Plan:");
        upgradeLabel.setBounds(550, 240, 150, 25);
        mainPanel.add(upgradeLabel);
        
        String[] plans = {"Basic", "Standard", "Deluxe"};
        planComboBox = new JComboBox<>(plans);
        planComboBox.setBounds(550, 420, 150, 25);
        mainPanel.add(planComboBox);
        
        upgradePlanBtn = new JButton("Upgrade Regular");
        upgradePlanBtn.setBounds(710, 420, 150, 30);
        upgradePlanBtn.setBackground(new Color(255, 152, 0));
        upgradePlanBtn.setForeground(Color.WHITE);
        upgradePlanBtn.addActionListener(this);
        mainPanel.add(upgradePlanBtn);
        
        // Adding a label for entering a payment amount
        JLabel paymentLabel = new JLabel("Payment Amount:");
        paymentLabel.setBounds(550, 315, 150, 25);
        mainPanel.add(paymentLabel);
        
        paymentAmountField = new JTextField();
        paymentAmountField.setBounds(550, 340, 150, 25);
        mainPanel.add(paymentAmountField);
        
        payDueBtn = new JButton("Pay Due");
        payDueBtn.setBounds(710, 340, 100, 25);
        payDueBtn.setBackground(new Color(56, 142, 60));
        payDueBtn.setForeground(Color.WHITE);
        payDueBtn.addActionListener(this);
        mainPanel.add(payDueBtn);
        
        // Adding a button to calculate a discount
        calculateDiscountBtn = new JButton("Calculate Discount");
        calculateDiscountBtn.setBounds(550, 375, 200, 30);
        calculateDiscountBtn.setBackground(new Color(0, 150, 136));
        calculateDiscountBtn.setForeground(Color.WHITE);
        calculateDiscountBtn.addActionListener(this);
        mainPanel.add(calculateDiscountBtn);
        
        // Setting up the regular member form panel
        regularPanel = new JPanel();
        regularPanel.setLayout(null);
        regularPanel.setBounds(0, 80, 900, 620);
        regularPanel.setBackground(new Color(240, 240, 240));
        regularPanel.setVisible(false);
        createRegularForm();
        frame.add(regularPanel);
            
        // Setting up the premium member form panel   
        premiumPanel = new JPanel();
        premiumPanel.setLayout(null);
        premiumPanel.setBounds(0, 80, 900, 620);
        premiumPanel.setBackground(new Color(240, 240, 240));
        premiumPanel.setVisible(false);
        createPremiumForm();
        frame.add(premiumPanel);
        
        // Showing the window and locking its size
        frame.setVisible(true);
        frame.setResizable(false); 
    }
    
    private void createRegularForm() {
        // Adding a title to the regular member form
        JLabel formTitle = new JLabel("REGULAR MEMBER FORM", JLabel.CENTER);
        formTitle.setBounds(0, 20, 900, 30);
        formTitle.setFont(new Font("Arial", Font.BOLD, 20));
        regularPanel.add(formTitle);

        // Adding a label and text box for member ID
        JLabel lblId = new JLabel("Member ID:");
        lblId.setBounds(100, 70, 150, 25);
        regularPanel.add(lblId);
        
        regTxtId = new JTextField();
        regTxtId.setBounds(250, 70, 200, 25);
        regularPanel.add(regTxtId);

        // Adding a label and text box for full name
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(100, 110, 150, 25);
        regularPanel.add(lblName);
        
        regTxtName = new JTextField();
        regTxtName.setBounds(250, 110, 200, 25);
        regularPanel.add(regTxtName);

        // Adding a label and text box for location
        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(100, 150, 150, 25);
        regularPanel.add(lblLocation);
        
        regTxtLocation = new JTextField();
        regTxtLocation.setBounds(250, 150, 200, 25);
        regularPanel.add(regTxtLocation);

        // Adding a label and text box for phone number
        JLabel lblPhone = new JLabel("Phone Number:");
        lblPhone.setBounds(100, 190, 150, 25);
        regularPanel.add(lblPhone);
        
        regTxtPhone = new JTextField();
        regTxtPhone.setBounds(250, 190, 200, 25);
        regularPanel.add(regTxtPhone);

        // Adding a label and text box for email address
        JLabel lblEmail = new JLabel("Email Address:");
        lblEmail.setBounds(100, 230, 150, 25);
        regularPanel.add(lblEmail);
        
        regTxtEmail = new JTextField();
        regTxtEmail.setBounds(250, 230, 200, 25);
        regularPanel.add(regTxtEmail);

        // Adding a label and radio buttons for gender
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(100, 270, 150, 25);
        regularPanel.add(lblGender);

        regRbMale = new JRadioButton("Male");
        regRbMale.setBounds(250, 270, 80, 25);
        regularPanel.add(regRbMale);

        regRbFemale = new JRadioButton("Female");
        regRbFemale.setBounds(340, 270, 80, 25);
        regularPanel.add(regRbFemale);

        regRbOther = new JRadioButton("Other");
        regRbOther.setBounds(430, 270, 80, 25);
        regularPanel.add(regRbOther);

        regGenderGroup = new ButtonGroup();
        // Grouping the gender radio buttons so only one can be picked
        regGenderGroup.add(regRbMale);
        regGenderGroup.add(regRbFemale);
        regGenderGroup.add(regRbOther);

        // Adding a label and text box for referral source
        JLabel lblReferral = new JLabel("Referral Source:");
        lblReferral.setBounds(100, 310, 150, 25);
        regularPanel.add(lblReferral);
        
        regTxtReferral = new JTextField();
        regTxtReferral.setBounds(250, 310, 200, 25);
        regularPanel.add(regTxtReferral);

        // Adding a label and dropdowns for date of birth
        JLabel lblDob = new JLabel("Date of Birth:");
        lblDob.setBounds(100, 350, 150, 25);
        regularPanel.add(lblDob);

        String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        dobDayComboBox = new JComboBox<>(days);
        dobDayComboBox.setBounds(250, 350, 50, 25);
        regularPanel.add(dobDayComboBox);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        dobMonthComboBox = new JComboBox<>(months);
        dobMonthComboBox.setBounds(310, 350, 60, 25);
        regularPanel.add(dobMonthComboBox);

        String[] years = {"1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006","2007","2008","2009","2010","2011","2012"};
        dobYearComboBox = new JComboBox<>(years);
        dobYearComboBox.setBounds(380, 350, 70, 25);
        regularPanel.add(dobYearComboBox);

        // Adding a label and dropdowns for membership start date
        JLabel lblMsDate = new JLabel("Membership Start Date:");
        lblMsDate.setBounds(100, 390, 150, 25);
        regularPanel.add(lblMsDate);

        msDayComboBox = new JComboBox<>(days);
        msDayComboBox.setBounds(250, 390, 50, 25);
        regularPanel.add(msDayComboBox);

        msMonthComboBox = new JComboBox<>(months);
        msMonthComboBox.setBounds(310, 390, 60, 25);
        regularPanel.add(msMonthComboBox);

        msYearComboBox = new JComboBox<>(years);
        msYearComboBox.setBounds(380, 390, 70, 25);
        regularPanel.add(msYearComboBox);

        // Adding buttons to the regular form
        regBtnClear = new JButton("Clear");
        regBtnClear.setBounds(250, 450, 100, 30);
        regBtnClear.addActionListener(this);
        regularPanel.add(regBtnClear);

        regBtnBack = new JButton("Back");
        regBtnBack.setBounds(360, 450, 100, 30);
        regBtnBack.addActionListener(this);
        regularPanel.add(regBtnBack);

        regBtnSubmit = new JButton("Submit");
        regBtnSubmit.setBounds(470, 450, 100, 30);
        regBtnSubmit.addActionListener(this);
        regularPanel.add(regBtnSubmit);

        // Adding a label and text box to show the plan
        JLabel planLabel = new JLabel("Plan:");
        planLabel.setBounds(550, 310, 100, 25);
        regularPanel.add(planLabel);

        plans = new JTextField("Basic");
        plans.setBounds(550, 340, 200, 25);
        plans.setEditable(false);
        regularPanel.add(plans);

        // Adding a label and text box to show the monthly price
        JLabel priceLabel = new JLabel("Monthly Price:");
        priceLabel.setBounds(550, 380, 100, 25);
        regularPanel.add(priceLabel);

        priceField = new JTextField("Rs.5000");
        priceField.setBounds(550, 410, 200, 25);
        priceField.setEditable(false);
        regularPanel.add(priceField);
    }

    private void createPremiumForm() {
        // Adding a title to the premium member form
        JLabel formTitle = new JLabel("PREMIUM MEMBER FORM", JLabel.CENTER);
        formTitle.setBounds(0, 20, 900, 30);
        formTitle.setFont(new Font("Arial", Font.BOLD, 20));
        premiumPanel.add(formTitle);

        // Adding a label and text box for member ID
        JLabel lblId = new JLabel("Member ID:");
        lblId.setBounds(100, 70, 150, 25);
        premiumPanel.add(lblId);
        
        premTxtId = new JTextField();
        premTxtId.setBounds(250, 70, 200, 25);
        premiumPanel.add(premTxtId);

        // Adding a label and text box for full name
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(100, 110, 150, 25);
        premiumPanel.add(lblName);
        
        premTxtName = new JTextField();
        premTxtName.setBounds(250, 110, 200, 25);
        premiumPanel.add(premTxtName);

        // Adding a label and text box for location
        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(100, 150, 150, 25);
        premiumPanel.add(lblLocation);
        
        premTxtLocation = new JTextField();
        premTxtLocation.setBounds(250, 150, 200, 25);
        premiumPanel.add(premTxtLocation);

        // Adding a label and text box for phone number
        JLabel lblPhone = new JLabel("Phone Number:");
        lblPhone.setBounds(100, 190, 150, 25);
        premiumPanel.add(lblPhone);
        
        premTxtPhone = new JTextField();
        premTxtPhone.setBounds(250, 190, 200, 25);
        premiumPanel.add(premTxtPhone);

        // Adding a label and text box for email address
        JLabel lblEmail = new JLabel("Email Address:");
        lblEmail.setBounds(100, 230, 150, 25);
        premiumPanel.add(lblEmail);
        
        premTxtEmail = new JTextField();
        premTxtEmail.setBounds(250, 230, 200, 25);
        premiumPanel.add(premTxtEmail);

        // Adding a label and radio buttons for gender
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(100, 270, 150, 25);
        premiumPanel.add(lblGender);

        premRbMale = new JRadioButton("Male");
        premRbMale.setBounds(250, 270, 80, 25);
        premiumPanel.add(premRbMale);

        premRbFemale = new JRadioButton("Female");
        premRbFemale.setBounds(340, 270, 80, 25);
        premiumPanel.add(premRbFemale);

        premRbOther = new JRadioButton("Other");
        premRbOther.setBounds(430, 270, 80, 25);
        premiumPanel.add(premRbOther);

        premGenderGroup = new ButtonGroup();
        // Grouping the gender radio buttons so only one can be picked
        premGenderGroup.add(premRbMale);
        premGenderGroup.add(premRbFemale);
        premGenderGroup.add(premRbOther);

        // Adding a label and text box for personal trainer
        JLabel lblTrainer = new JLabel("Personal Trainer:");
        lblTrainer.setBounds(100, 310, 150, 25);
        premiumPanel.add(lblTrainer);
        
        premTxtTrainer = new JTextField();
        premTxtTrainer.setBounds(250, 310, 200, 25);
        premiumPanel.add(premTxtTrainer);

        // Adding a label and dropdowns for date of birth
        JLabel lblDob = new JLabel("Date of Birth:");
        lblDob.setBounds(100, 350, 150, 25);
        premiumPanel.add(lblDob);

        String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        premDobDayComboBox = new JComboBox<>(days);
        premDobDayComboBox.setBounds(250, 350, 50, 25);
        premiumPanel.add(premDobDayComboBox);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        premDobMonthComboBox = new JComboBox<>(months);
        premDobMonthComboBox.setBounds(310, 350, 60, 25);
        premiumPanel.add(premDobMonthComboBox);

        String[] years = {"2010", "2011", "2012", "2013", "2014", "2015", 
                     "2016", "2017", "2018", "2019", "2020", "2021",
                     "2022", "2023", "2024", "2025"};
        premDobYearComboBox = new JComboBox<>(years);
        premDobYearComboBox.setBounds(380, 350, 70, 25);
        premiumPanel.add(premDobYearComboBox);

        // Adding a label and dropdowns for membership start date
        JLabel lblMsDate = new JLabel("Membership Start Date:");
        lblMsDate.setBounds(100, 390, 150, 25);
        premiumPanel.add(lblMsDate);

        premMsDayComboBox = new JComboBox<>(days);
        premMsDayComboBox.setBounds(250, 390, 50, 25);
        premiumPanel.add(premMsDayComboBox);

        premMsMonthComboBox = new JComboBox<>(months);
        premMsMonthComboBox.setBounds(310, 390, 60, 25);
        premiumPanel.add(premMsMonthComboBox);

        premMsYearComboBox = new JComboBox<>(years);
        premMsYearComboBox.setBounds(380, 390, 70, 25);
        premiumPanel.add(premMsYearComboBox);

        // Adding buttons to the premium form
        premBtnClear = new JButton("Clear");
        premBtnClear.setBounds(250, 450, 100, 30);
        premBtnClear.addActionListener(this);
        premiumPanel.add(premBtnClear);

        premBtnBack = new JButton("Back");
        premBtnBack.setBounds(360, 450, 100, 30);
        premBtnBack.addActionListener(this);
        premiumPanel.add(premBtnBack);

        premBtnSubmit = new JButton("Submit");
        premBtnSubmit.setBounds(470, 450, 100, 30);
        premBtnSubmit.addActionListener(this);
        premiumPanel.add(premBtnSubmit);
        
        // Adding a label and text box for premium plan charge
        JLabel lblPlanCharge = new JLabel("Premium Plan Charge:");
        lblPlanCharge.setBounds(600, 110, 150, 25);
        premiumPanel.add(lblPlanCharge);
        
        JTextField txtPlanCharge = new JTextField();
        txtPlanCharge.setBounds(760, 110, 120, 25);
        txtPlanCharge.setEditable(false);
        txtPlanCharge.setText("50000"); 
        premiumPanel.add(txtPlanCharge);

        // Adding a label and text box for discount amount
        JLabel lblDiscount = new JLabel("Discount Amount:");
        lblDiscount.setBounds(600, 150, 150, 25);
        premiumPanel.add(lblDiscount);
        
        JTextField txtDiscount = new JTextField();
        txtDiscount.setBounds(760, 150, 120, 25);
        txtDiscount.setEditable(false);
        txtDiscount.setText("0"); 
        premiumPanel.add(txtDiscount);
    }
    
    // This helps figure out which gender was picked
    private String getSelectedGender(JRadioButton male, JRadioButton female, JRadioButton other) {
        if (male.isSelected()) return "Male";
        if (female.isSelected()) return "Female";
        if (other.isSelected()) return "Other";
        return "Not Specified";
    }

    // This checks if a member ID is already taken
    private boolean isDuplicateId(int id) {
        for (GymMember member : memberList) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Checking which button was clicked and doing the right thing
        if (e.getSource() == btnAddRegular) {
            showRegularForm();
        }
        else if (e.getSource() == btnAddPremium) {
            showPremiumForm();
        }
        else if (e.getSource() == regBtnClear) {
            clearRegularForm();
        }
        else if (e.getSource() == premBtnClear) {
            clearPremiumForm();
        }
        else if (e.getSource() == btnActivate) {
            activateMembership();
        }
        else if (e.getSource() == btnDeactivate) {
            deactivateMembership();
        }
        else if (e.getSource() == btnMarkAttendance) {
            markMemberAttendance();
        }
        else if (e.getSource() == btnDisplayRegular) {
            displayRegularMembers();
        }
        else if (e.getSource() == btnDisplayPremium) {
            displayPremiumMembers();
        }
        else if (e.getSource() == btnRevertRegular) {
            revertRegularMember();
        }
        else if (e.getSource() == btnRevertPremium) {
            revertPremiumMember();
        }
        else if (e.getSource() == btnSaveToFile) {
            saveToFile();
        }
        else if (e.getSource() == btnReadFromFile) {
            readFromFile();
        }
        else if (e.getSource() == regBtnBack || e.getSource() == premBtnBack) {
            showMainPanel();
        }
        else if (e.getSource() == regBtnSubmit) {
            addRegularMember();
        }
        else if (e.getSource() == premBtnSubmit) {
            addPremiumMember();
        }
        else if (e.getSource() == upgradePlanBtn) {
            upgradeMemberPlan();
        }
        else if (e.getSource() == calculateDiscountBtn) {
            calculateMemberDiscount();
        }
        else if (e.getSource() == payDueBtn) {
            payMemberDueAmount();
        }
    }
    private void saveToFile() {
    try {
        // Opening a file to write member details
        FileWriter writer = new FileWriter("MemberDetails.txt");

        // Writing a header with column names
        writer.write("=========================================================================================================================================================================================\n");
        writer.write(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-12s %-16s %-15s %-15s %-18s %-16s\n",
                "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", "Plan", "Price", "Attendance",
                "Loyalty Points", "Active Status", "Full Payment", "Discount Amount", "Net Amount Paid"));
        writer.write("=========================================================================================================================================================================================\n");

        // Going through each member and saving their info
        for (GymMember member : memberList) {
            // Printing to the console to check what’s being saved
            System.out.println("Saving member - ID: " + member.getId() + 
                               ", Attendance: " + member.getAttendance() + 
                               ", Loyalty Points: " + member.getLoyaltyPoints());
            
            if (member instanceof RegularMember) {
                RegularMember reg = (RegularMember) member;
                // Writing regular member details to the file
                writer.write(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10d %-12d %-16.2f %-15s %-15s %-18s %-16d\n",
                        member.getId(),
                        member.getName(),
                        member.getLocation(),
                        member.getPhone(),
                        member.getEmail(),
                        member.getMembershipStartDate(),
                        reg.getPlan(),
                        (int) reg.getPrice(),
                        member.getAttendance(),
                        member.getLoyaltyPoints(),
                        member.getActiveStatus(),
                        "NA",
                        "NA",
                        (int) reg.getPrice()));
            } else if (member instanceof PremiumMember) {
                PremiumMember prem = (PremiumMember) member;
                // Writing premium member details to the file
                writer.write(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10d %-12d %-16.2f %-15s %-15s %-18.2f %-16d\n",
                        member.getId(),
                        member.getName(),
                        member.getLocation(),
                        member.getPhone(),
                        member.getEmail(),
                        member.getMembershipStartDate(),
                        "12 months",
                        50000,
                        member.getAttendance(),
                        member.getLoyaltyPoints(),
                        member.getActiveStatus(),
                        prem.getIsFullPayment(),
                        prem.getDiscountAmount(),
                        (int) prem.getPaidAmount()));
            }
        }   

        // Closing the file and showing a success message
        writer.close();
        JOptionPane.showMessageDialog(frame, "Member details saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException ex) {
        // Showing an error if saving fails
        JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
private void readFromFile() {
    try {
        // Creating a reader to open the file
        FileReader reader = new FileReader("MemberDetails.txt");
        
        // Creating a new window to show the file content
        JFrame frame = new JFrame("Member Data");
        JTextArea textArea = new JTextArea();
        frame.add(new JScrollPane(textArea));
        frame.setSize(600, 400);
        
        // Reading the file one character at a time
        int character;
        StringBuilder content = new StringBuilder();
        while ((character = reader.read()) != -1) {
            content.append((char) character);
        }
        
        // Showing the content in the window
        textArea.setText(content.toString());
        frame.setVisible(true);
        
        // Closing the reader
        reader.close();
    } catch (FileNotFoundException e) {
        // Showing an error if the file isn’t found
        JOptionPane.showMessageDialog(null, "File not found!");
    } catch (IOException e) {
        // Showing an error if reading fails
        JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
    }
}
    private void upgradeMemberPlan() {
        // Getting the member ID from the input
        String inputId = txtMemberIdInput.getText().trim();
        
        if (inputId.isEmpty()) {
            // Showing an error if no ID is entered
            JOptionPane.showMessageDialog(frame, "Please enter a Member ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Turning the ID into a number
            int memberId = Integer.parseInt(inputId);
            // Finding the member with that ID
            GymMember member = findMemberById(memberId);
            
            if (member == null) {
                // Showing an error if no member is found
                JOptionPane.showMessageDialog(frame, "No member found with ID: " + memberId, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!(member instanceof RegularMember)) {
                // Showing an error if it’s not a regular member
                JOptionPane.showMessageDialog(frame, "Only regular members can upgrade plans", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!member.getActiveStatus()) {
                // Showing an error if the membership isn’t active
                JOptionPane.showMessageDialog(frame, "Member must be active to upgrade plan", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Getting the chosen plan and upgrading it
            String selectedPlan = (String) planComboBox.getSelectedItem();
            RegularMember regularMember = (RegularMember) member;
            String result = regularMember.upgradePlan(selectedPlan);
            JOptionPane.showMessageDialog(frame, result, "INFO", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(frame, "Invalid ID format - numbers only", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculateMemberDiscount() {
        // Getting the member ID from the input
        String inputId = txtMemberIdInput.getText().trim(); //Getting the input
        
        if (inputId.isEmpty()) {
            // Showing an error if no ID is entered
            JOptionPane.showMessageDialog(frame, "Please enter a Member ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Turning the ID into a number
            int memberId = Integer.parseInt(inputId);
            // Finding the member with that ID
            GymMember member = findMemberById(memberId); // memberId stored here 
            
            if (member == null) {
                // Showing an error if no member is found
                JOptionPane.showMessageDialog(frame, "No member found with ID: " + memberId, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!(member instanceof PremiumMember)) {
                // Showing an error if it’s not a premium member
                JOptionPane.showMessageDialog(frame, "Only premium members can calculate discounts", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Calculating the discount for the member
            PremiumMember premiumMember = (PremiumMember) member; // converts member to premium member type so we can use methods of premium member too
            premiumMember.calculateDiscount();
            
            String message = "Discount calculated for ID: " + memberId;
            if (premiumMember.getIsFullPayment()) {
                message += "\nDiscount Amount: Rs." + premiumMember.getDiscountAmount();
            } else {
                message += "\nNo discount available - payment not complete";
            }
            JOptionPane.showMessageDialog(frame, message, "Discount Info", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(frame, "Invalid ID format - numbers only", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void payMemberDueAmount() {
        // Getting the member ID and payment amount
        String inputId = txtMemberIdInput.getText().trim();
        String amountStr = paymentAmountField.getText().trim();
        
        if (inputId.isEmpty() || amountStr.isEmpty()) {
            // Showing an error if either field is empty
            JOptionPane.showMessageDialog(frame, "Please enter both Member ID and Amount", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Turning the inputs into numbers
            int memberId = Integer.parseInt(inputId);
            double amount = Double.parseDouble(amountStr);
            
            if (amount <= 0) {
                // Showing an error if the amount isn’t positive
                JOptionPane.showMessageDialog(frame, "Payment amount must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Finding the member with that ID
            GymMember member = findMemberById(memberId);
            
            if (member == null) {
                // Showing an error if no member is found
                JOptionPane.showMessageDialog(frame, "No member found with ID: " + memberId, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!(member instanceof PremiumMember)) {
                // Showing an error if it’s not a premium member
                JOptionPane.showMessageDialog(frame, "Only premium members can make payments", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Processing the payment
            PremiumMember premiumMember = (PremiumMember) member;
            String result = premiumMember.payDueAmount(amount);
            JOptionPane.showMessageDialog(frame, result, "Payment Result", JOptionPane.INFORMATION_MESSAGE);
            
            // Clearing the payment field after success
            paymentAmountField.setText("");
            
        } catch (NumberFormatException ex) {
            // Showing an error if the inputs aren’t numbers
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for ID and Amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMainPanel() {
        // Showing the main menu and hiding the forms
        mainPanel.setVisible(true);
        regularPanel.setVisible(false);
        premiumPanel.setVisible(false);
    }

    private void showRegularForm() {
        // Hiding the main menu and showing the regular form
        mainPanel.setVisible(false);
        regularPanel.setVisible(true);
        premiumPanel.setVisible(false);
    }

    private void showPremiumForm() {
        // Hiding the main menu and showing the premium form
        mainPanel.setVisible(false);
        regularPanel.setVisible(false);
        premiumPanel.setVisible(true);
    }

    private void clearRegularForm() {
        // Clearing all text fields in the regular form
        regTxtId.setText("");
        regTxtName.setText("");
        regTxtLocation.setText("");
        regTxtPhone.setText("");
        regTxtEmail.setText("");
        regTxtReferral.setText("");
        
        // Clearing the gender selection
        regGenderGroup.clearSelection();
        
        // Resetting the date of birth dropdowns
        dobDayComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobYearComboBox.setSelectedIndex(0);
        
        // Resetting the membership start date dropdowns
        msDayComboBox.setSelectedIndex(0);
        msMonthComboBox.setSelectedIndex(0);
        msYearComboBox.setSelectedIndex(0);
        
        // Putting the cursor back on the ID field
        regTxtId.requestFocusInWindow();
    }

    private void clearPremiumForm() {
        // Clearing all text fields in the premium form
        premTxtId.setText("");
        premTxtName.setText("");
        premTxtLocation.setText("");
        premTxtPhone.setText("");
        premTxtEmail.setText("");
        premTxtTrainer.setText("");
        
        // Clearing the gender selection
        premGenderGroup.clearSelection();
        
        // Resetting the date of birth dropdowns
        premDobDayComboBox.setSelectedIndex(0);
        premDobMonthComboBox.setSelectedIndex(0);
        premDobYearComboBox.setSelectedIndex(0);
        
        // Resetting the membership start date dropdowns
        premMsDayComboBox.setSelectedIndex(0);
        premMsMonthComboBox.setSelectedIndex(0);
        premMsYearComboBox.setSelectedIndex(0);
        
        // Putting the cursor back on the ID field
        premTxtId.requestFocusInWindow();
    }

    private void addRegularMember() {
        if (!validateRegularForm()) return; // if validation fails the rest of the function also doesn't execute
        try {
            // Getting the member info from the form
            int id = Integer.parseInt(regTxtId.getText());
            String name = regTxtName.getText();
            String location = regTxtLocation.getText();
            String phone = regTxtPhone.getText();
            String email = regTxtEmail.getText();
            String gender = getSelectedGender(regRbMale, regRbFemale, regRbOther);
            String referralSource = regTxtReferral.getText();
            
            // Putting together the date of birth
            String dobDay = (String) dobDayComboBox.getSelectedItem();
            String dobMonth = (String) dobMonthComboBox.getSelectedItem();
            String dobYear = (String) dobYearComboBox.getSelectedItem();
            String dob = dobDay + "-" + dobMonth + "-" + dobYear;
            
            // Putting together the membership start date
            String msDay = (String) msDayComboBox.getSelectedItem();
            String msMonth = (String) msMonthComboBox.getSelectedItem();
            String msYear = (String) msYearComboBox.getSelectedItem();
            String startDate = msDay + "-" + msMonth + "-" + msYear;

            if (isDuplicateId(id)) {
                // Showing an error if the ID is already used
                JOptionPane.showMessageDialog(frame, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Creating a new regular member
            RegularMember member = new RegularMember(id, name, location, phone, email, 
                                                  gender, dob, startDate, referralSource);
            memberList.add(member);
            
            // Showing a success message and logging it
            JOptionPane.showMessageDialog(frame, "Regular member added successfully!");
            System.out.println("Regular Member added - ID: " + id + ", Name: " + name);
            clearRegularForm();
            showMainPanel();
            
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(frame, "Please enter a valid numeric ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Showing an error if something else goes wrong
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPremiumMember() {
        if (!validatePremiumForm()) return;
        try {
            // Getting the member info from the form
            int id = Integer.parseInt(premTxtId.getText());
            String name = premTxtName.getText();
            String location = premTxtLocation.getText();
            String phone = premTxtPhone.getText();
            String email = premTxtEmail.getText();
            String gender = getSelectedGender(premRbMale, premRbFemale, premRbOther);
            String personalTrainer = premTxtTrainer.getText();
            
            // Putting together the date of birth
            String dobDay = (String) premDobDayComboBox.getSelectedItem();
            String dobMonth = (String) premDobMonthComboBox.getSelectedItem();
            String dobYear = (String) premDobYearComboBox.getSelectedItem();
            String dob = dobDay + "-" + dobMonth + "-" + dobYear;
            
            // Putting together the membership start date
            String msDay = (String) premMsDayComboBox.getSelectedItem(); // returns object so we casted to string
            String msMonth = (String) premMsMonthComboBox.getSelectedItem();
            String msYear = (String) premMsYearComboBox.getSelectedItem();
            String startDate = msDay + "-" + msMonth + "-" + msYear;
            // Checking for duplicate IDs
            if (isDuplicateId(id)) {
                // Showing an error if the ID is already used
                JOptionPane.showMessageDialog(frame, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Creating a new premium member
            PremiumMember member = new PremiumMember(id, name, location, phone, email, 
                                                      gender, dob, startDate, personalTrainer);
            memberList.add(member);
            JOptionPane.showMessageDialog(frame, "Premium member added successfully!");
            System.out.println("Premium Member added - ID: " + id + ", Name: " + name);
            clearPremiumForm();
            showMainPanel();
            
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(frame, "Please enter a valid numeric ID", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Showing an error if something else goes wrong
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    private boolean validateRegularForm() {
        // Checking if required fields are filled
        if (regTxtId.getText().trim().isEmpty() || 
            regTxtName.getText().trim().isEmpty() ||
            regTxtEmail.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(frame, 
                "Please fill in all required fields", 
                "Missing Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    private boolean validatePremiumForm() {
        if (premTxtId.getText().trim().isEmpty() || 
            premTxtName.getText().trim().isEmpty() ||
            premTxtTrainer.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(frame,
                "Please fill in all required fields",
                "Missing Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void revertRegularMember() {
        try {
            // Getting the member ID to revert
            int memberId = Integer.parseInt(txtMemberIdInput.getText().trim());// parse int converts to integer if string is inputted
            RegularMember memberToRevert = null; // datatype of RegularMember and sets up to null which holds nothing now 
            
            // Finding the regular member to revert
            for (GymMember member : memberList) { // goes through memberList object
                if (member instanceof RegularMember && member.getId() == memberId) {
                    memberToRevert = (RegularMember) member;
                    break;
                }
            }
            // Basically finds a regular member with a matching ID and puts in memberToRevert
            // Instance of checks whether it belongs to that
            
            if (memberToRevert != null) {
                // Asking for a reason to revert the member
                String removalReason = JOptionPane.showInputDialog(
                    frame, 
                    "Enter removal reason:", 
                    "Member Revert", 
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (removalReason == null || removalReason.trim().isEmpty()) {
                    removalReason = "No reason provided";
                }
                
                // Reverting the member with the reason
                memberToRevert.revertRegularMember(removalReason.trim());
                
                JOptionPane.showMessageDialog(
                    frame, 
                    "Regular member with ID " + memberId + " has been reverted\n" +
                    "Plan reset to: " + memberToRevert.getPlan() + "\n" +
                    "Removal Reason: " + removalReason,
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // Showing an error if the member isn’t found
                JOptionPane.showMessageDialog(
                    frame, 
                    "Regular member with ID " + memberId + " not found",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(
                frame, 
                "Please enter a valid numeric ID", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void revertPremiumMember() {
        try {
            // Getting the member ID to revert
            int memberId = Integer.parseInt(txtMemberIdInput.getText().trim());
            PremiumMember premiumMemberToRevert = null;
            
            // Finding the premium member to revert
            for (GymMember member : memberList) {
                if (member instanceof PremiumMember && member.getId() == memberId) {
                    premiumMemberToRevert = (PremiumMember) member;
                    break;
                }
            }
            
            if (premiumMemberToRevert != null) {
                // Reverting the premium member
                premiumMemberToRevert.revertPremiumMember();
                
                JOptionPane.showMessageDialog(
                    frame, 
                    "Premium member with ID " + memberId + " has been reverted\n" +
                    "Trainer: " + premiumMemberToRevert.getPersonalTrainer() + "\n" +
                    "Payment Status: " + (premiumMemberToRevert.getIsFullPayment() ? "Paid" : "Not Paid") + "\n" +
                    "Paid Amount: " + premiumMemberToRevert.getPaidAmount() + "\n" +
                    "Discount: " + premiumMemberToRevert.getDiscountAmount(),
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // Showing an error if the member isn’t found
                JOptionPane.showMessageDialog(
                    frame, 
                    "Premium member with ID " + memberId + " not found",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(
                frame, 
                "Please enter a valid numeric ID", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void displayRegularMembers() {
        // Creating a new window to show regular members
        JFrame displayFrame = new JFrame("Regular Members");
        displayFrame.setSize(1000, 600);
        displayFrame.setLayout(null); 
        

        JTextArea textArea = new JTextArea();
        // Setting a simple font for the text
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // PLAIN IS A PUBLIC STATIC INT inside the font class
        // creates a new Font obj
        
        // Creating a scroll pane and setting its size
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 970, 500); //x,y,w,h
        
        // Adding headers and a line to style it
        textArea.append(String.format("%-5s %-20s %-15s %-12s %-10s %-15s %-12s %-10s %-8s\n", 
            "ID", "Name", "Location", "Phone", "Gender", "Plan", "Email","Points", "DOB", "Status"));
        textArea.append("----------------------------------------------------------------------------------------------\n");
        
        // Adding the details of regular members
        for (GymMember member : memberList) {
            if (member instanceof RegularMember) {
                RegularMember regularMember = (RegularMember) member;
                textArea.append(String.format("%-5d %-20s %-15s %-12s %-10s %-15s %-12s %-10s %-8s\n",
                    member.getId(),
                    member.getName(),
                    member.getLocation(),
                    member.getPhone(),
                    member.getGender(),
                    regularMember.getPlan(),
                    member.getEmail(),
                    (int)member.getLoyaltyPoints(), // because it is in double 
                    member.getDOB(),
                    member.getActiveStatus() ? "Active" : "Inactive"));
            }
        }

        // Adding the scroll pane to the frame
        displayFrame.add(scrollPane);
        displayFrame.setVisible(true);
    }

    private void displayPremiumMembers() {
        // Creating a new window to show premium members
        JFrame displayFrame = new JFrame("Premium Members");
        displayFrame.setSize(1200, 600);
        displayFrame.setLayout(null);

        JTextArea textArea = new JTextArea();
        // Setting a simple font for the text
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setBounds(10, 10, 1160, 500); 
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 1160, 500); 

        // Adding headers and a line to style it
        textArea.append(String.format("%-5s %-20s %-15s %-12s %-25s %-8s %-12s %-15s %-15s %-8s %-8s\n", 
            "ID", "Name", "Location", "Phone", "Email", "Gender", "DOB", "Start Date", "Trainer", "Points", "Status"));
        textArea.append("--------------------------------------------------------------------------------------------------------------------\n");

        // Adding the details of premium members
        for (GymMember member : memberList) {
            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                textArea.append(String.format("%-5d %-20s %-15s %-12s %-25s %-8s %-12s %-15s %-15s %-8d %-8s\n",
                    member.getId(),
                    member.getName(),
                    member.getLocation(),
                    member.getPhone(),
                    member.getEmail(),
                    member.getGender(),
                    member.getDOB(),
                    member.getMembershipStartDate(),
                    premium.getPersonalTrainer(),
                    (int)member.getLoyaltyPoints(),
                    member.getActiveStatus() ? "Active" : "Inactive"));
            }
        }
        displayFrame.add(scrollPane);
        displayFrame.setVisible(true);
    }
    
    private void markMemberAttendance() {
    if (txtMemberIdInput.getText().trim().isEmpty()) {
        // Showing an error if no ID is entered
        JOptionPane.showMessageDialog(frame, 
            "Please enter a Member ID", 
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Turning the ID into a number
        int memberId = Integer.parseInt(txtMemberIdInput.getText().trim());
        // Finding the member with that ID
        GymMember member = findMemberById(memberId);
        
        if (member == null) {
            // Showing an error if no member is found
            JOptionPane.showMessageDialog(frame, 
                "Member ID " + memberId + " not found!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!member.getActiveStatus()) {
            // Showing an error if the membership isn’t active
            JOptionPane.showMessageDialog(frame, 
                "Cannot mark attendance - membership is inactive!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Saving the old attendance and points
        int prevAttendance = member.getAttendance();
        double prevPoints = member.getLoyaltyPoints();
        
        // Marking the attendance
        member.markAttendance();
        
        // Printing to the console to check the update
        System.out.println("After marking attendance - ID: " + memberId + 
                           ", Attendance: " + member.getAttendance() + 
                           ", Loyalty Points: " + member.getLoyaltyPoints());
        
        // Showing a success message with the changes
        JOptionPane.showMessageDialog(frame, 
            "Attendance marked for ID: " + memberId +
            "\nPrevious attendance: " + prevAttendance +
            "\nNew attendance: " + member.getAttendance() +
            "\nLoyalty points added: " + (member.getLoyaltyPoints() - prevPoints),
            "Success", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Attendance Marked");
    } catch (NumberFormatException ex) {
        // Showing an error if the ID isn’t a number
        JOptionPane.showMessageDialog(frame, 
            "Please enter a valid numeric ID", 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    

    private void activateMembership() {
        try {
            // Getting the member ID to activate
            int memberId = Integer.parseInt(txtMemberIdInput.getText());
            // Finding the member with that ID
            GymMember member = findMemberById(memberId); // CALLING THE findMemberId method 
            
            if (member != null) {
                // Activating the membership
                member.activeMembership();
                JOptionPane.showMessageDialog(frame, "Membership activated successfully for ID: " + memberId);
            } else {
                // Showing an error if no member is found
                JOptionPane.showMessageDialog(frame, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(frame, "Please enter a valid numeric ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deactivateMembership() {
        try {
            // Getting the member ID to deactivate
            int memberId = Integer.parseInt(txtMemberIdInput.getText());
            // Finding the member with that ID
            GymMember member = findMemberById(memberId);
            
            if (member != null) {
                // Deactivating the membership
                member.deactivateMembership();
                JOptionPane.showMessageDialog(frame, "Membership deactivated successfully for ID: " + memberId);
            } else {
                // Showing an error if no member is found
                JOptionPane.showMessageDialog(frame, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Showing an error if the ID isn’t a number
            JOptionPane.showMessageDialog(frame, "Please enter a valid numeric ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // This helps find a member by their ID
    private GymMember findMemberById(int id) {
        for (GymMember member : memberList) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Starting the gym system
        new GymGUI();
    }
}