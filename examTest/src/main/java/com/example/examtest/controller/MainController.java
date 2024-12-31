package com.example.examtest.controller;

import com.example.examtest.entities.Consultation;
import com.example.examtest.entities.Medecin;
import com.example.examtest.entities.Patient;
import com.example.examtest.metier.ICabinetImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private ICabinetImpl cabinet = new ICabinetImpl();

    @FXML
    private TextField cinInput;

    @FXML
    private TableColumn<Patient, String> cinPatient;

    @FXML
    private TableView<Consultation> consultationTable;

    @FXML
    private TableColumn<Consultation, Date> dateConsultation;

    @FXML
    private DatePicker dateInput;

    @FXML
    private TableColumn<Patient, Date> datenaissPatiet;

    @FXML
    private TextField emailInput;

    @FXML
    private TableColumn<Patient, String> emailMedecin;

    @FXML
    private TableColumn<Patient, String> emailPatient;

    @FXML
    private TableColumn<Consultation, String> idConMedecin;

    @FXML
    private TableColumn<Consultation, String> idConPatient;

    @FXML
    private TableColumn<Consultation, Integer> idConsultation;

    @FXML
    private TextField idInput;

    @FXML
    private TableColumn<Medecin, Integer> idMedecin;

    @FXML
    private TextField idMedecintInput;

    @FXML
    private TableColumn<Patient, Integer> idPatient;

    @FXML
    private TableView<Medecin> medecinTable;

    @FXML
    private TextField nomInput;
    @FXML
    private TextField emailMedecintInput1;
    @FXML
    private TableColumn<Medecin, String> nomMedecin;

    @FXML
    private TextField nomMedecintInput;

    @FXML
    private TableColumn<Patient, String> nomPatient;

    @FXML
    private TextField prenomInput;

    @FXML
    private TableColumn<Medecin, String> prenomMedecin;

    @FXML
    private TextField prenomMedecintInput;

    @FXML
    private TableColumn<Patient, String> prenomPatient;

    @FXML
    private TextField searchPatientInput;

    @FXML
    private TableView<Patient> tablePatients;

    @FXML
    private TableColumn<Medecin, String> telMedecin;

    @FXML
    private TableColumn<Patient, String> telePatient;

    @FXML
    private TextField telephoneInput;

    @FXML
    private TextField telephoneMedecintInput;
    @FXML
    private TextField idConsultationInput;
    @FXML
    private ComboBox<String> listeMedecinForConsultation;
    @FXML
    private ComboBox<String> listePatientForConsultation;
    @FXML
    private TextField nomConsultationInput;

    @FXML
    void addMedecin(ActionEvent event) {
        Medecin medecin = new Medecin(Integer.parseInt(idMedecintInput.getText()), nomMedecintInput.getText(),prenomMedecintInput.getText(), emailMedecintInput1.getText(), telephoneMedecintInput.getText());
        cabinet.saveMedecin(medecin);
        medecins.add(medecin);
    }

    @FXML
    void addPatient(MouseEvent event) {
        LocalDate time = dateInput.getValue();
        java.sql.Date sqlDate = java.sql.Date.valueOf(time);
        Patient patient = new Patient(Integer.parseInt(idInput.getText()), nomInput.getText(),prenomInput.getText(), cinInput.getText(), telephoneInput.getText(), emailInput.getText(), sqlDate);
        cabinet.savePatient(patient);
        patients.add(patient);
    }

    @FXML
    void removeMedecin(ActionEvent event) {
        Medecin selectedMedecin = medecinTable.getSelectionModel().getSelectedItem();
        cabinet.deleteMedecin(selectedMedecin.getId_medecin());
        medecins.remove(selectedMedecin);
    }

    @FXML
    void removePatient(MouseEvent event) {
        Patient selectedPatient = tablePatients.getSelectionModel().getSelectedItem();
        cabinet.deletePatient(selectedPatient.getId_patient());
        patients.remove(selectedPatient);
    }

    @FXML
    void rowDepartementClicked(MouseEvent event) {

    }

    @FXML
    void searchPatientByNom(ActionEvent event) {

    }

    @FXML
    void updateProf(ActionEvent event) {

    }
    ObservableList<Patient> patients = FXCollections.observableArrayList(
            cabinet.getAllPatient()
    );
    ObservableList<Medecin> medecins = FXCollections.observableArrayList(
            cabinet.getAllMedecin()
    );
    ObservableList<Consultation> consultations = FXCollections.observableArrayList(
            cabinet.getAllConsultation()
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPatient.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
        nomPatient.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPatient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cinPatient.setCellValueFactory(new PropertyValueFactory<>("cin"));
        telePatient.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailPatient.setCellValueFactory(new PropertyValueFactory<>("email"));
        datenaissPatiet.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tablePatients.setItems(patients);
        //Display Medecins
        idMedecin.setCellValueFactory(new PropertyValueFactory<>("id_medecin"));
        nomMedecin.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomMedecin.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailMedecin.setCellValueFactory(new PropertyValueFactory<>("email"));
        telMedecin.setCellValueFactory(new PropertyValueFactory<>("tel"));
        medecinTable.setItems(medecins);
        //Display all Patients in Consultation
        patients.forEach(patient -> {
            listePatientForConsultation.getItems().add(patient.getId_patient()+" - Patient: "+patient.getNom());
        });
        //Display all Medecins in Consultation
        medecins.forEach(medecin -> {
            listeMedecinForConsultation.getItems().add(medecin.getId_medecin()+" - Docteur: "+medecin.getNom());
        });
    }
    @FXML
    void addConsultation(ActionEvent event) {

    }
    @FXML
    void removeConsultation(ActionEvent event) {

    }
}
