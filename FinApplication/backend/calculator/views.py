import json
from decimal import Decimal, ROUND_HALF_UP
from django.http import JsonResponse
from django.views.decorators.http import require_http_methods


@require_http_methods(["POST"])
def emi_calculator(request):
    try:
        payload = json.loads(request.body)
        principal = Decimal(str(payload.get('principal', 0)))
        annual_rate = Decimal(str(payload.get('annual_rate', 0)))
        tenure_months = int(payload.get('tenure_months', 0))

        if principal <= 0 or annual_rate < 0 or tenure_months <= 0:
            return JsonResponse(
                {'error': 'principal > 0, annual_rate >= 0, tenure_months > 0 are required.'},
                status=400,
            )

        years = Decimal(tenure_months) / Decimal(12)
        simple_interest = (principal * annual_rate * years) / Decimal(100)
        total_payment = principal + simple_interest
        emi = total_payment / Decimal(tenure_months)

        q = Decimal('0.01')
        return JsonResponse(
            {
                'principal': float(principal.quantize(q, rounding=ROUND_HALF_UP)),
                'annual_rate': float(annual_rate.quantize(q, rounding=ROUND_HALF_UP)),
                'tenure_months': tenure_months,
                'simple_interest': float(simple_interest.quantize(q, rounding=ROUND_HALF_UP)),
                'total_payment': float(total_payment.quantize(q, rounding=ROUND_HALF_UP)),
                'emi': float(emi.quantize(q, rounding=ROUND_HALF_UP)),
            }
        )
    except (ValueError, TypeError, json.JSONDecodeError):
        return JsonResponse({'error': 'Invalid input payload.'}, status=400)
