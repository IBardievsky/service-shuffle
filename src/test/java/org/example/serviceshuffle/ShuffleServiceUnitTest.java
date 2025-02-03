package org.example.serviceshuffle;

import org.example.serviceshuffle.dto.ShuffleRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShuffleServiceUnitTest {

    @Mock
    private AsyncLogService asyncLogService;

    @InjectMocks
    private ShuffleService shuffleService;

    @Test
    void testShuffle() {
        int numbers = 5;
        ShuffleRequest request = new ShuffleRequest(numbers);
        ResponseEntity<List<Integer>> response = shuffleService.shuffle(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        List<Integer> shuffledArray = response.getBody();
        assertEquals(numbers, shuffledArray.size());

        // Verify async logging was called
        verify(asyncLogService).logRequestAsync(any(ShuffleRequest.class));
    }
}
