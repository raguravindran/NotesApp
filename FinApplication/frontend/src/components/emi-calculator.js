import { LitElement, css, html } from 'lit';

class EmiCalculator extends LitElement {
  static properties = {
    principal: { type: Number },
    annualRate: { type: Number },
    tenureMonths: { type: Number },
    result: { state: true },
    error: { state: true },
    loading: { state: true },
  };

  static styles = css`
    :host {
      display: block;
      max-width: 520px;
      margin: 40px auto;
      font-family: Arial, sans-serif;
      color: #102a43;
    }

    .card {
      border: 1px solid #d9e2ec;
      border-radius: 12px;
      padding: 20px;
      box-shadow: 0 10px 24px rgba(16, 42, 67, 0.08);
    }

    h2 {
      margin-top: 0;
    }

    label {
      font-weight: 600;
      display: block;
      margin-top: 12px;
      margin-bottom: 6px;
    }

    input {
      width: 100%;
      box-sizing: border-box;
      padding: 10px;
      border: 1px solid #bcccdc;
      border-radius: 8px;
      font-size: 14px;
    }

    button {
      margin-top: 16px;
      padding: 10px 16px;
      border: none;
      border-radius: 8px;
      background: #0b69ff;
      color: white;
      font-size: 14px;
      cursor: pointer;
    }

    button:disabled {
      opacity: 0.6;
      cursor: not-allowed;
    }

    .result,
    .error {
      margin-top: 16px;
      padding: 12px;
      border-radius: 8px;
    }

    .result {
      background: #f0fff4;
      border: 1px solid #9ae6b4;
    }

    .error {
      background: #fff5f5;
      border: 1px solid #feb2b2;
    }
  `;

  constructor() {
    super();
    this.principal = 120000;
    this.annualRate = 10;
    this.tenureMonths = 24;
    this.result = null;
    this.error = '';
    this.loading = false;
  }

  async calculate() {
    this.loading = true;
    this.error = '';
    this.result = null;

    try {
      const response = await fetch('http://127.0.0.1:8000/api/emi/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          principal: this.principal,
          annual_rate: this.annualRate,
          tenure_months: this.tenureMonths,
        }),
      });

      const data = await response.json();
      if (!response.ok) {
        throw new Error(data.error || 'Failed to calculate EMI');
      }

      this.result = data;
    } catch (e) {
      this.error = e.message;
    } finally {
      this.loading = false;
    }
  }

  render() {
    return html`
      <div class="card">
        <h2>EMI Calculator (Simple Interest)</h2>

        <label for="principal">Principal Amount</label>
        <input
          id="principal"
          type="number"
          min="1"
          .value=${String(this.principal)}
          @input=${(e) => (this.principal = Number(e.target.value))}
        />

        <label for="annualRate">Annual Interest Rate (%)</label>
        <input
          id="annualRate"
          type="number"
          min="0"
          step="0.01"
          .value=${String(this.annualRate)}
          @input=${(e) => (this.annualRate = Number(e.target.value))}
        />

        <label for="tenureMonths">Tenure (Months)</label>
        <input
          id="tenureMonths"
          type="number"
          min="1"
          .value=${String(this.tenureMonths)}
          @input=${(e) => (this.tenureMonths = Number(e.target.value))}
        />

        <button ?disabled=${this.loading} @click=${this.calculate}>
          ${this.loading ? 'Calculating...' : 'Calculate EMI'}
        </button>

        ${this.error
          ? html`<div class="error">${this.error}</div>`
          : ''}

        ${this.result
          ? html`
              <div class="result">
                <div><strong>Simple Interest:</strong> ₹${this.result.simple_interest.toFixed(2)}</div>
                <div><strong>Total Payment:</strong> ₹${this.result.total_payment.toFixed(2)}</div>
                <div><strong>Monthly EMI:</strong> ₹${this.result.emi.toFixed(2)}</div>
              </div>
            `
          : ''}
      </div>
    `;
  }
}

customElements.define('emi-calculator', EmiCalculator);
