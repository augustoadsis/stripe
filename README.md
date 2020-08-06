# Basic Stripe API Integration
In this example you going to know how to integrate with Stripe Payment API, creating customers, attach payments to them, and charge/refound payment intents.

### Requirements
First of all, you should generate the API Key and API Secret, so please follow the instructions bellow.

* [Create keys](https://dashboard.stripe.com/register)

After done it, replace the following values with the genereted on your Stripe account (previous step)
```
stripe:
  apiKey: aaaaaaaaa
  secretKey: zzzzzzzzzz
```

### API
Here is the reference of used methods.

* [Create costumer](https://stripe.com/docs/api/customers)
* [Create payment method](https://stripe.com/docs/api/payment_methods)
* [Create payment intent](https://stripe.com/docs/api/payment_intents)
* [Create refund](https://stripe.com/docs/api/refunds)

