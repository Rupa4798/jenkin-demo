package com.fsd.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fsd.entity.Payment;
import com.fsd.repository.PaymentRepository;


import java.time.LocalDateTime;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Base64;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public String makePayment(Long registrationId, String paymentMethod) {
        // Retrieve registration details from the database
        Registration registration = registrationRepository.findById(registrationId).orElse(null);
        if (registration == null) {
            return "Registration not found";
        }

        // Calculate payment amount
        double paymentAmount = calculatePaymentAmount(registration);

        // Create payment object
        Payment payment = new Payment();
        payment.setAmount(paymentAmount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentMethod);
        payment.setRegistration(registration);

        // Save payment to the database or perform other necessary actions
        paymentRepository.save(payment);

        // Generate QR code for the payment
        String qrCode = generateQRCode(payment);

        return "Payment "+paymentAmount+" successful. Thank you for your payment! QR Code: " + qrCode;
    }

    
    private String generateQRCode(Payment payment) {
        String paymentInfo = "Payment ID: " + payment.getId() + ", Amount: " + payment.getAmount() +
                ", Payment Date: " + payment.getPaymentDate() + ", Payment Method: " + payment.getPaymentMethod();
        // Generate QR code from payment information
        byte[] qrCodeBytes = generateQRCodeImage(paymentInfo);
        // Convert byte array to Base64 string
        return Base64.getEncoder().encodeToString(qrCodeBytes);
    }

    private byte[] generateQRCodeImage(String text) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int size = 300;
            BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, size, size);
            graphics.setColor(Color.BLACK);
            // Your QR code generation logic here (using libraries like ZXing)
            // Example using ZXing:
            // BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, size, size);
            // MatrixToImageWriter.writeToStream(matrix, "PNG", stream);
            ImageIO.write(image, "PNG", stream);
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private double calculatePaymentAmount(Registration registration) {
        Event event = registration.getEvent();
        double entryFee = event.getEntryFee();
        int numberOfAdults = registration.getNumberOfAdults();
        int numberOfChildren = registration.getNumberOfChildren();
        double foodCost = calculateFoodCost(registration.getEvent().getFoodOptions(), numberOfAdults, numberOfChildren);

        // Calculate total payment amount
        return (entryFee * (numberOfAdults + numberOfChildren)) + foodCost;
    }

    private double calculateFoodCost(List<FoodOption> foodOptions, int numberOfAdults, int numberOfChildren) {
        double totalFoodCost = 0.0;

        for (FoodOption foodOption : foodOptions) {
            // Assuming each food option has a price per adult and per child
            totalFoodCost += (foodOption.getPrice() * numberOfAdults) + (foodOption.getPrice() * 0.5 * numberOfChildren);
        }

        return totalFoodCost;
    }
}

