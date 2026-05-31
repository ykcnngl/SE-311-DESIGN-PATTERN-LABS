abstract class Transaction {
    public final void processTransaction() {
        verifyIdentity();
        checkBalance();
        executeTransfer();
        updateLedger();
        sendNotification();
    }
    protected void verifyIdentity() {
        System.out.println("Verifying identity...");
    }
    protected void checkBalance() {
        System.out.println("Checking available balance in savings account.");
    }
    protected abstract void executeTransfer();
    protected void updateLedger() {
        System.out.println("Updating transaction ledger.");
    }
    protected void sendNotification() {
        System.out.println("Sending notification to customer...");
    }
}
class CashWithdrawal extends Transaction {
    @Override
    protected void executeTransfer() {
        System.out.println("Dispensing cash from ATM.");
    }
}
class InternationalTransfer extends Transaction {
    @Override
    protected void executeTransfer() {
        performAMLComplianceCheck();
        System.out.println("Processing SWIFT transfer to the destination bank.");
    }
    private void performAMLComplianceCheck() {
        System.out.println("Performing AML compliance check and fraud screening...");
    }
}
public class LAB8 { //TEMPLATE           FLIGHT CONTROL
    public static void main(String[] args) {
        System.out.println("[CashWithdrawal]");
        Transaction cashWithdrawal = new CashWithdrawal();
        cashWithdrawal.processTransaction();
        System.out.println("\n----------------------------\n");
        System.out.println("[InternationalTransfer]");
        Transaction internationalTransfer = new InternationalTransfer();
        internationalTransfer.processTransaction();
    }
}