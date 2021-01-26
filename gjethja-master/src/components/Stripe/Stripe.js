import React, {useState} from "react";
import { connect } from "react-redux";
import  { makePayment } from "../../actions/stripe";
import {CardElement, useStripe, useElements} from '@stripe/react-stripe-js';
import mapStateToProps from "react-redux/lib/connect/mapStateToProps";

const Stripe = ({ id, makePayment, onPaymentFinish }) => {
    const stripe = useStripe();
    const elements = useElements();

    const [price, setPrice] = useState(3.0);

    const handleSubmit = async (event) => {
        // Block native form submission.
        event.preventDefault();

        if (!stripe || !elements) {
            // Stripe.js has not loaded yet. Make sure to disable
            // form submission until Stripe.js has loaded.
            return;
        }

        // Get a reference to a mounted CardElement. Elements knows how
        // to find your CardElement because there can only ever be one of
        // each type of element.
        const cardElement = elements.getElement(CardElement);

        // Use your card Element with other Stripe.js APIs
        const result = await stripe.createToken(cardElement);

        if (result.error) {
            console.log('[error]', result.error);
        } else {
            console.log('[PaymentMethod]', result);
            makePayment(id, {
                key: result.token.id,
                amount: price
            })
            onPaymentFinish();
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="btn-group w-100 btn-group-toggle mb-4" data-toggle="buttons">
                <label className={`btn btn-secondary ${price === 3.0 && "active"}`}>
                    <input type="radio" name="options" id="option1" onClick={(e) => setPrice(3.0)} checked={price === 3.0} /> 3 ditë (3€)
                </label>
                <label className={`btn btn-secondary ${price === 5.0 && "active"}`}>
                    <input type="radio" name="options" id="option2" onClick={(e) => setPrice(5.0)} checked={price === 5.0} /> 5 ditë (5€)
                </label>
                <label className={`btn btn-secondary ${price === 7.0 && "active"}`}>
                    <input type="radio" name="options" id="option3" onClick={(e) => setPrice(7.0)} checked={price === 7.0} /> 7 ditë (7€)
                </label>
            </div>
            <CardElement />
            <button type="submit"
                    disabled={!stripe}
                    className="btn-block mt-3">
                Pay
            </button>
        </form>
    );
};

const mapDispatchToProps = dispatch => ({
    makePayment: (id, data) => dispatch(makePayment(id, data))
})

export default connect(null, mapDispatchToProps)(Stripe);