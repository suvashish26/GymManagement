
public class PremiumMember extends GymMember
{
    // instance variables - replace the example below with your own
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    /**
     * Constructor for objects of class PremiumMember
     */
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate,String personalTrainer)
    {
        // initialise instance variables
        super(id,name,location,phone,email,gender,DOB,membershipStartDate);
        this.premiumCharge=50000.0;
        this.personalTrainer = personalTrainer; //Use this keyword when parameter names match attribute names
        this.paidAmount=0;
        this.isFullPayment=false;
        this.discountAmount=0.0;
    }
    // Each attribute must have accessor method so
    public final double getPremiumCharge(){
        return premiumCharge;
    }       
    public String getPersonalTrainer(){
        return personalTrainer;
    }
    public boolean getIsFullPayment(){
        return isFullPayment;
    }
    public double getPaidAmount(){
        return paidAmount;
    }
    public double getDiscountAmount(){
        return discountAmount;
    }
    @Override
    public void markAttendance(){
        attendance = attendance + 1;
        loyaltyPoints = loyaltyPoints + 10;
        
    }
    public String payDueAmount(double paidAmount)
    {
        if (this.isFullPayment == true){
            return "Payment is already full";
        }
        // increase the amount than before
        if (this.paidAmount+paidAmount > premiumCharge){
            //this.paidAmount -= paidAmount;
            //return "Your payment is greater than the required amount";
            return "Your payment is greater than the required amount " + (premiumCharge - paidAmount);
        }
        this.paidAmount = this.paidAmount+paidAmount;
        if (this.paidAmount == premiumCharge){
            isFullPayment= true;
            return "Your payment is a success";
            
        }
        double remainingAmount = (premiumCharge - paidAmount);// Corrected to use updated paidAmount
        return "Your remainingAmount is " + remainingAmount;
        
    }
    public void calculateDiscount(){
        if (isFullPayment == true){
            discountAmount = 0.1 * premiumCharge;
            System.out.println("Your Discounted Amount is " + discountAmount);
        }       
    }
    
    public void revertPremiumMember(){
        resetMember();
        personalTrainer="";
        isFullPayment= false;
        paidAmount= 0;
        discountAmount= 0;
    }
    @Override
    public void display(){
        super.display();
        System.out.println("The name of the trainer is: "+ this.personalTrainer);
        System.out.println("The paid amount is: " + this.paidAmount);
        System.out.println("The full payment is: "+ this.isFullPayment);
        double remainingAmount = premiumCharge - this.paidAmount;
        System.out.println("The remainingAmount is: " + remainingAmount);
        if (isFullPayment){
            System.out.println("The discount amount is: " + this.discountAmount);
        }
    }
}
