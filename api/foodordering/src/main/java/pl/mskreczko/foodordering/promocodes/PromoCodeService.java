package pl.mskreczko.foodordering.promocodes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class PromoCodeService {

    private final PromoCodeRepository promoCodeRepository;

    public boolean verifyCode(String code) {
        return promoCodeRepository.existsByCode(code);
    }

    public double getDiscount(String code) {
        return promoCodeRepository.findByCode(code).getDiscount();
    }

    @Transactional
    public String generateCode(Double discount) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append((char)(random.nextInt((90 + 1) - 65) + 65));
        sb.append((char)(random.nextInt((90 + 1) - 65) + 65));
        sb.append((char)(random.nextInt((90 + 1) - 65) + 65));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(11));
        sb.append((char)(random.nextInt((90 + 1) - 65) + 65));

        promoCodeRepository.save(PromoCode.builder().code(sb.toString()).discount(discount).build());

        return sb.toString();
    }
}
