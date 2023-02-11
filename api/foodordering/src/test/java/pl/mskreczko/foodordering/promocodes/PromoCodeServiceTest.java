package pl.mskreczko.foodordering.promocodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PromoCodeServiceTest {

    @Mock
    private PromoCodeRepository promoCodeRepository;

    @InjectMocks
    private PromoCodeService promoCodeService;

    @Test
    void verifyCode_should_return_true() {
        Mockito.when(promoCodeRepository.existsByCode("ABC12D")).thenReturn(true);

        var result = promoCodeService.verifyCode("ABC12D");

        Assertions.assertTrue(result);
    }

    @Test
    void verifyCode_should_return_false() {
        Mockito.when(promoCodeRepository.existsByCode("ABC12D")).thenReturn(false);

        var result = promoCodeService.verifyCode("ABC12D");

        Assertions.assertFalse(result);
    }

    @Test
    void generateCode_should_return_true() {
        var code = promoCodeService.generateCode(10.0);

        Assertions.assertEquals(6, code.length());
        Assertions.assertTrue(code.matches("^[A-Z]{3}[0-9]{2}[A-Z]$"));
    }
}
