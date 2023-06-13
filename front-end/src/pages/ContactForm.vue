<template>
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form @submit.prevent="send">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" name="email" id="email"
                            placeholder="Inserisci la tua email" v-model="contact.email" required>
                    </div>
                    <div class="mb-3">
                        <label for="message" class="form-label">Messaggio</label>
                        <textarea class="form-control" name="message" v-model="contact.message" id="message" rows="5"
                            placeholder="Inserisci il tuo messaggio" required></textarea>
                    </div>
                    <div class="nuttons d-flex justify-content-between">
                        <button type="button" class="btn btn-secondary">Indietro</button>
                        <button type="submit" @click="sendContact" class="btn btn-primary">Invia</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>
<script>
const API_URL = "http://127.0.0.1:8080/api/v1/";
import axios from "axios";
export default {
    data() {
        return {
            contact: {
                email: "",
                message: "",
                success: true,
            }
        }
    },
    methods: {
        send() {
            axios.
                post(API_URL + "contact", this.contact)
                .then((response) => {
                    console.log(response)
                    this.$router.push('/');
                })
                .catch((error) => {
                    console.error(error);
                })
        }
    },
}
</script>
<style lang="scss"></style>