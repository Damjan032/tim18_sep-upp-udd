package itcompany.ftn.paymentserviceprovider.util;

import itcompany.ftn.paymentserviceprovider.dto.BankCardCreateInvoiceDTO;
import itcompany.ftn.paymentserviceprovider.dto.InvoiceDTO;
import itcompany.ftn.paymentserviceprovider.dto.InvoiceItemDTO;
import itcompany.ftn.paymentserviceprovider.dto.UserRegistrationDTO;
import itcompany.ftn.paymentserviceprovider.model.Invoice;
import itcompany.ftn.paymentserviceprovider.model.InvoiceItem;
import itcompany.ftn.paymentserviceprovider.model.User;
import itcompany.ftn.paymentserviceprovider.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class EntityMapper {

    public static User userRegistrationDTOtoUser(UserRegistrationDTO dto) {

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setActive(true);
        user.setRequiresPasswordChange(false);
        user.setRole(Role.ROLE_WEB_SHOP_ADMIN);
        return user;
    }

    public static InvoiceDTO invoiceToInvoiceDTO(Invoice invoice) {
        List<InvoiceItemDTO> items = new ArrayList<>();
        for (InvoiceItem item : invoice.getInvoiceItems()) {
            InvoiceItemDTO itemDTO = new InvoiceItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setName(item.getName());
            itemDTO.setAmount(item.getAmount());
            itemDTO.setDescription(item.getDescription());
            itemDTO.setCurrency(item.getCurrency());
            items.add(itemDTO);
        }

        InvoiceDTO dto = new InvoiceDTO(invoice.getId(), invoice.getWebShop().getId(), invoice.getAmount(), invoice.getCurrency(),  items);
        return dto;
    }

    public static BankCardCreateInvoiceDTO invoiceToBankCardInvoiceDTO(Invoice invoice, String successRedirectUrl, String failureRedirectUrl, String errorRedirectUrl) {
        BankCardCreateInvoiceDTO dto = new BankCardCreateInvoiceDTO();
        dto.setMerchantOrderId(invoice.getId());
        dto.setWebShopId(invoice.getWebShop().getId());
        dto.setAmount(invoice.getAmount());
        dto.setCurrency(invoice.getCurrency());
        dto.setSuccessRedirectUrl(successRedirectUrl);
        dto.setFailureRedirectUrl(failureRedirectUrl);
        dto.setErrorRedirectUrl(errorRedirectUrl);
        return dto;
    }

}
