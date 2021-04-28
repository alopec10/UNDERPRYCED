package com.inso2.inso2.service.alert;

import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateAlertService {
    private final AlertRepository alertRepository;

    public CreateAlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void createBuyAlert(Order o){
        ProductDetails pd = o.getProductDetails();
        Product p = pd.getProduct();
        String title = "Compra efectuada";
        String text = "Se ha realizado una compra del producto " + p.getTitle() +", talla " + pd.getSize() + ", por un valor de " + o.getPrice() + "€"
                +"\nUn saludo";
        Alert alert = new Alert();
        alert.setTitle(title);
        alert.setMessage(text);
        alert.setRead(false);
        alert.setDate(new Date());
        alert.setUser(o.getBuyer());
        alertRepository.saveAndFlush(alert);
    }

    public void createSellAlert(Order o){
        ProductDetails pd = o.getProductDetails();
        Product p = pd.getProduct();
        String title = "Venta efectuada";
        String text = "Se ha realizado una venta del producto " + p.getTitle() +", talla " + pd.getSize() + ", por un valor de " + o.getPrice() + "€"
                +"\nUn saludo";
        Alert alert = new Alert();
        alert.setTitle(title);
        alert.setMessage(text);
        alert.setRead(false);
        alert.setDate(new Date());
        alert.setUser(o.getSeller());
        alertRepository.saveAndFlush(alert);
    }

    public void createOrderApprovedAlert(Order o){
        String title = "Pedido aprobado";
        String text = "El pedido con referencia " + o.getRef() + " ha sido verificado por uno de nuestros trabajadores, recibirás un pago en tu cuenta en el plazo de 3 días hábiles.\n"
                +"\nUn saludo";
        Alert alert = new Alert();
        alert.setTitle(title);
        alert.setMessage(text);
        alert.setRead(false);
        alert.setDate(new Date());
        alert.setUser(o.getSeller());
        alertRepository.saveAndFlush(alert);
    }

    public void createOrderSentAlert(Order o, Shipment shipment){
        String title = "Pedido enviado";
        String text = "El pedido con referencia " + o.getRef() + " ha sido enviado. La fecha de entrega prevista es la siguiente: " + shipment.getArrivalDate().toString().substring(0, 10) + "\n"
                +"\nUn saludo!";
        Alert alert = new Alert();
        alert.setTitle(title);
        alert.setMessage(text);
        alert.setRead(false);
        alert.setDate(new Date());
        alert.setUser(o.getSeller());
        alertRepository.saveAndFlush(alert);
    }

    public void createWelcomeAlert(User user){
        String title = "Bievenido!";
        String text = "Bienvenido a la plataforma, " + user.getName() + "estamos encantados de que hayas decidido confiar en nosotros\n"
                + "Nos gustaría recordarte que para realizar tanto una puja como una oferta es totalmente necesario disponer tanto de un método de pago activo como de una dirección completa.\n"
                +"Esperemos que disfrutes lo máximo posible de todas las funcionalidades de la aplicación.\n"
                +"Un saludo!";
        Alert alert = new Alert();
        alert.setTitle(title);
        alert.setMessage(text);
        alert.setRead(false);
        alert.setDate(new Date());
        alert.setUser(user);
        alertRepository.saveAndFlush(alert);
    }
}
