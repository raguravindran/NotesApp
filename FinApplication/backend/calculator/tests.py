from django.test import TestCase, Client


class EmiApiTests(TestCase):
    def setUp(self):
        self.client = Client()

    def test_emi_success(self):
        response = self.client.post(
            '/api/emi/',
            data='{"principal": 120000, "annual_rate": 10, "tenure_months": 24}',
            content_type='application/json',
        )
        self.assertEqual(response.status_code, 200)
        data = response.json()
        self.assertEqual(data['simple_interest'], 24000.0)
        self.assertEqual(data['total_payment'], 144000.0)
        self.assertEqual(data['emi'], 6000.0)

    def test_emi_invalid_payload(self):
        response = self.client.post(
            '/api/emi/',
            data='{"principal": -1, "annual_rate": 10, "tenure_months": 24}',
            content_type='application/json',
        )
        self.assertEqual(response.status_code, 400)
