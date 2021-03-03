package ming.sandbox;

import java.security.interfaces.RSAPublicKey;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWKSTest {
    public static void main(String[] args) throws JwkException {
        final String encryptedToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkRTSFVCOkpXSzpLRVkifQ.eyJzY29wZSI6WyJzZXJ2aWNlLmFsbCJdLCJleHAiOjE2MTQ3MDkxMjEsImF1dGhvcml0aWVzIjpbInNlcnZpY2UiXSwianRpIjoiY2E4bi1BZ2Vhekh6WUF6bXBteE4tcUxxaHJnIiwiY2xpZW50X2lkIjoiZHNodWItZGF0YS1jYXRhbG9nLWNsaWVudC1pZCJ9.Pq9UIV26VtEZ4XBBsT3y7QWPd3rkpm_lD825LgeTC2RkvSSMv0BG1d1B8TOPTETUe9FBhQS37Uxp1_7FxBFmzLMNnHAd-avsSGTaSBxfG1dZDwcXxO2FFPKGPMthQZUdTmQM-lCZiSXmyx5DziDf4C23_A-PGGDeqSSt3N2JCjnEmtszAd1szBzfmVmSrdEB5JkRD8nwagYDNw6yuFFfI_nGueK6OddBY2CXuv6eKnF3uV_HK5LyJszuIWsLFJS9LjXl2ZAW6YCtJDWVLMTtp2Vct2HXUJs0RuWMFI0P9D-Z-IZSCaIwFq8jNg7amk7Y6CeMoOXIpybbTOdEbwO3W893dm7DPynjgCcrx6fvgn5ekvM_ClkLaEws_jslWY_711k1WDJZDokGeoLPPSEHnEj-FeTTyIeoVu8RAqVQFY9IxUKuFLbQUzCDzN8vIq3iLl1bGNvQso3WxCtHyrWgJpyegJoP1qZO_K1l1I-y1s-O-_9SWBhhn6q1ebr5ukF6tnzvYwymIboATPOwoHn-IqIGyRSti6DhJ3x4MFj8H8mDXViImSUz3eBp3d59MW-4GozSQHI6HzGkNtzxYjyKiSz5PGh92Uiji68cIRUtenfVmGvspsZmGumennNIiAJ1CEhkuRf1406-SjReueuolR_V_MYDN81O25F9uQvyLac";
        DecodedJWT jwt = JWT.decode(encryptedToken);
        JwkProvider provider = new UrlJwkProvider("http://localhost:8000");
        Jwk jwk = provider.get(jwt.getKeyId());
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        algorithm.verify(jwt);
    }
}
