package com.inso2.inso2.service.paymentMethod;

import com.inso2.inso2.dto.user.payment.PaymentMethodRequest;
import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.PaymentMethodRepository;
import com.inso2.inso2.security.Encrypter;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreatePaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public CreatePaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void create(PaymentMethodRequest req, User user) throws Exception{
        if (!this.isNumberValid(req.getNumber()) || !this.isCvvValid(req.getCvv()) || !this.isMonthValid(req.getExpMonth()) || !this.isYearValid(req.getExpYear())) {
            throw new Exception("Invalid data format");
        }
        PaymentMethod paymentMethod = paymentMethodRepository.findByUserAndNumber(user, Encrypter.encrypt(req.getNumber()));
        if (paymentMethod == null) {
            paymentMethod = new PaymentMethod();
            paymentMethod.setNumber(Encrypter.encrypt(req.getNumber()));
            paymentMethod.setUser(user);
            paymentMethod.setDefaultMethod(req.isDefaultMethod());
        }
        paymentMethod.setName(req.getName());
        paymentMethod.setCvv(Encrypter.encrypt(req.getCvv()));
        paymentMethod.setExpMonth(Encrypter.encrypt(req.getExpMonth()));
        paymentMethod.setExpYear(Encrypter.encrypt(req.getExpYear()));
        paymentMethod.setActive(true);
        paymentMethodRepository.save(paymentMethod);
    }

    private boolean isNumberValid(String number){
        String patterns
                = "^4[0-9]{12}(?:[0-9]{3})?$"
                + "|^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$"
                + "|^3[47][0-9]{13}$"
                + "|^3(?:0[0-5]|[68][0-9])[0-9]{11}$"
                + "|^6(?:011|5[0-9]{2})[0-9]{12}$"
                + "|^(?:2131|1800|35\\d{3})\\d{11}$";
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    private boolean isCvvValid(String cvv){
        Pattern pattern = Pattern.compile("^[0-9]{3,4}$");
        Matcher matcher = pattern.matcher(cvv);
        return matcher.matches();
    }

    private boolean isMonthValid(String month){
        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])$");
        Matcher matcher = pattern.matcher(month);
        return matcher.matches();
    }

    private boolean isYearValid(String year){
        Pattern pattern = Pattern.compile("^[0-9]{2}$");
        Matcher matcher = pattern.matcher(year);
        if(matcher.matches()){
            int y = Calendar.getInstance().get(Calendar.YEAR);
            String s = "20" + year;
            return Integer.parseInt(s) >= y;
        }
        return false;
    }
}
