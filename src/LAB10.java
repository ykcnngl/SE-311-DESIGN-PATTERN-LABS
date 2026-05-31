import java.util.ArrayList;

interface PatientRecord {
    void Accept(PatientRecordVisitor visitor);
}

interface PatientRecordVisitor {
    void Visit(NewbornInfantRecord element);
    void Visit(PostSurgeryAdultRecord element);
    void Visit(ElderlyResidentRecord element);
    void Visit(CriticalCarePatientRecord element);
}

abstract class Patient implements PatientRecord {
    private String id;
    private int heartRateBpm;
    private int mobilityScore;
    private int sleepHoursPerDay;

    public Patient(String id, int heartRateBpm, int mobilityScore, int sleepHoursPerDay) {
        this.id = id;
        this.heartRateBpm = heartRateBpm;
        this.mobilityScore = mobilityScore;
        this.sleepHoursPerDay = sleepHoursPerDay;
    }

    public String getId() { return id; }
    public int getHeartRateBpm() { return heartRateBpm; }
    public int getMobilityScore() { return mobilityScore; }
    public int getSleepHoursPerDay() { return sleepHoursPerDay; }
}

class NewbornInfantRecord extends Patient {
    public NewbornInfantRecord(String id, int heartRate, int mobility, int sleep) {
        super(id, heartRate, mobility, sleep);
    }
    public void Accept(PatientRecordVisitor visitor) {
        visitor.Visit(this);
    }
}

class PostSurgeryAdultRecord extends Patient {
    public PostSurgeryAdultRecord(String id, int heartRate, int mobility, int sleep) {
        super(id, heartRate, mobility, sleep);
    }
    public void Accept(PatientRecordVisitor visitor) {
        visitor.Visit(this);
    }
}

class ElderlyResidentRecord extends Patient {
    public ElderlyResidentRecord(String id, int heartRate, int mobility, int sleep) {
        super(id, heartRate, mobility, sleep);
    }
    public void Accept(PatientRecordVisitor visitor) {
        visitor.Visit(this);
    }
}

class CriticalCarePatientRecord extends Patient {
    public CriticalCarePatientRecord(String id, int heartRate, int mobility, int sleep) {
        super(id, heartRate, mobility, sleep);
    }
    public void Accept(PatientRecordVisitor visitor) {
        visitor.Visit(this);
    }
}

class HospitalManagementSystem {
    private ArrayList<PatientRecord> patients = new ArrayList<PatientRecord>();

    public void Add(PatientRecord patient) {
        patients.add(patient);
    }

    public void Remove(PatientRecord patient) {
        patients.remove(patient);
    }

    public void Accept(PatientRecordVisitor visitor) {
        for (PatientRecord p : patients) {
            p.Accept(visitor);
        }
    }
}

class BillingDepartmentVisitor implements PatientRecordVisitor {
    public void Visit(NewbornInfantRecord element) {
        System.out.println("Billing Newborn [" + element.getId() + "]: Incubator costs applied.");
    }
    public void Visit(PostSurgeryAdultRecord element) {
        System.out.println("Billing Adult [" + element.getId() + "]: Monitoring costs applied.");
    }
    public void Visit(ElderlyResidentRecord element) {
        System.out.println("Billing Elderly [" + element.getId() + "]: Care sensors applied.");
    }
    public void Visit(CriticalCarePatientRecord element) {
        System.out.println("Billing Critical Care [" + element.getId() + "]: ICU Ventilator costs applied.");
    }
}

class ResearchDivisionVisitor implements PatientRecordVisitor {
    public void Visit(NewbornInfantRecord element) {
        System.out.println("Research: Newborn data " + element.getId());
    }
    public void Visit(PostSurgeryAdultRecord element) {
        System.out.println("Research: Adult data " + element.getId());
    }
    public void Visit(ElderlyResidentRecord element) {
        System.out.println("Research: Elderly data " + element.getId());
    }
    public void Visit(CriticalCarePatientRecord element) {
        System.out.println("Research: Critical patient data " + element.getId());
    }
}

public class LAB10 {//HospitalApp
    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();

        hms.Add(new NewbornInfantRecord("N-101", 125, 5, 18));
        hms.Add(new PostSurgeryAdultRecord("A-202", 80, 4, 8));
        hms.Add(new ElderlyResidentRecord("E-303", 72, 2, 9));
        hms.Add(new CriticalCarePatientRecord("C-404", 110, 0, 4));

        hms.Accept(new BillingDepartmentVisitor());
        hms.Accept(new ResearchDivisionVisitor());
    }
}