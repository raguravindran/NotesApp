# FinApplication - EMI Calculator (Lit + Django)

This repository now includes a dedicated `FinApplication/` environment containing a full-stack **EMI calculator** based on **simple interest**.

- **Frontend:** Lit JS Web Component (`FinApplication/frontend/`)
- **Backend:** Python Django API (`FinApplication/backend/`)

## Project structure

- `FinApplication/frontend/`: Vite + Lit web app with `<emi-calculator>` component
- `FinApplication/backend/`: Django project exposing EMI calculation endpoint

## Formula used (Simple Interest)

- `SI = (P × R × T) / 100`
- `Total Payment = P + SI`
- `EMI = Total Payment / Number of Months`

Where:
- `P` = principal
- `R` = annual rate in %
- `T` = tenure in years

## Backend setup (Django)

```bash
cd FinApplication/backend
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
python manage.py migrate
python manage.py runserver
```

Backend runs at: `http://127.0.0.1:8000`

API endpoint:

- `POST /api/emi/`
- Sample JSON body:

```json
{
  "principal": 120000,
  "annual_rate": 10,
  "tenure_months": 24
}
```

## Frontend setup (Lit)

```bash
cd FinApplication/frontend
npm install
npm run dev
```

Frontend runs at: `http://localhost:5173`

The app calls Django API at `http://127.0.0.1:8000/api/emi/`.

## Run tests (backend)

```bash
cd FinApplication/backend
source .venv/bin/activate
python manage.py test
```
