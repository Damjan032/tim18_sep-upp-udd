package itcompany.ftn.bitcoinpaymentservice.service;

public interface BankAccountService {

    void delete(String webShopId);

    boolean save(String webShopId, String merchantId, String merchantPassword, String bankName);
}
