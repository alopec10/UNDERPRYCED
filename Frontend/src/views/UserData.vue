<template>
  <div class="my-10">
    <h1 class="text-5xl font-semibold" style="font-family: 'Quantico'">
      DATOS DEL USUARIO
    </h1>
    <div class="inline-block w-1/2 mt-5">
      <form class="mt-10">
        <div class="grid gap-6 grid-cols-2 my-6 mx-auto items-center">
          <div class="floating-input mb-5 relative">
            <input
              v-model="name"
              type="text"
              id="name"
              class="
                border-2 border-purple-500
                focus:outline-none
                rounded-xl
                hover:bg-purple-100
                focus:bg-purple-100
                w-full
                p-3
                h-16
              "
              placeholder="Nombre"
              autocomplete="off"
              @blur="$v.name.$touch()"
            />
            <label
              for="name"
              class="
                absolute
                top-0
                left-0
                px-3
                py-5
                h-full
                pointer-events-none
                transform
                origin-left
                transition-all
                duration-100
                ease-in-out
              "
              >Nombre</label
            >
            <div
              v-if="nameErrors.length !== 0"
              class="text-sm mt-2 text-red-400 absolute"
            >
              {{ nameErrors[0] }}
            </div>
          </div>

          <div class="floating-input mb-5 relative">
            <input
              v-model="surname"
              type="text"
              id="surname"
              class="
                border-2 border-purple-500
                focus:outline-none
                rounded-xl
                hover:bg-purple-100
                focus:bg-purple-100
                w-full
                p-3
                h-16
              "
              placeholder="Apellidos"
              autocomplete="off"
              @blur="$v.surname.$touch()"
            />
            <label
              for="surname"
              class="
                absolute
                top-0
                left-0
                px-3
                py-5
                h-full
                pointer-events-none
                transform
                origin-left
                transition-all
                duration-100
                ease-in-out
              "
              >Apellidos</label
            >
            <div
              v-if="surnameErrors.length !== 0"
              class="text-sm mt-2 text-red-400 absolute"
            >
              {{ surnameErrors[0] }}
            </div>
          </div>

          <div class="floating-input mb-5 relative">
            <input
              v-model="email"
              type="text"
              id="email"
              class="
                border-2 border-purple-500
                focus:outline-none
                rounded-xl
                hover:bg-purple-100
                focus:bg-purple-100
                w-full
                p-3
                h-16
              "
              placeholder="Email"
              autocomplete="off"
              @blur="$v.email.$touch()"
            />
            <label
              for="email"
              class="
                absolute
                top-0
                left-0
                px-3
                py-5
                h-full
                pointer-events-none
                transform
                origin-left
                transition-all
                duration-100
                ease-in-out
              "
              >Email</label
            >
            <div
              v-if="emailErrors.length !== 0"
              class="text-sm mt-2 text-red-400 absolute"
            >
              {{ emailErrors[0] }}
            </div>
          </div>

          <div class="floating-input mb-5 relative">
            <input
              v-model="phoneNumber"
              type="text"
              id="phoneNumber"
              class="
                border-2 border-purple-500
                focus:outline-none
                rounded-xl
                hover:bg-purple-100
                focus:bg-purple-100
                w-full
                p-3
                h-16
              "
              placeholder="Teléfono"
              autocomplete="off"
              @blur="$v.phoneNumber.$touch()"
            />
            <label
              for="phoneNumber"
              class="
                absolute
                top-0
                left-0
                px-3
                py-5
                h-full
                pointer-events-none
                transform
                origin-left
                transition-all
                duration-100
                ease-in-out
              "
              >Teléfono</label
            >
            <div
              v-if="phoneNumberErrors.length !== 0"
              class="text-sm mt-2 text-red-400 absolute"
            >
              {{ phoneNumberErrors[0] }}
            </div>
          </div>

          <div class="floating-input mb-5 relative">
            <input
              v-model="password"
              type="password"
              id="password"
              class="
                border-2 border-purple-500
                focus:outline-none
                rounded-xl
                hover:bg-purple-100
                focus:bg-purple-100
                w-full
                p-3
                h-16
              "
              placeholder="Contraseña"
              autocomplete="off"
              @blur="$v.password.$touch()"
            />
            <label
              for="password"
              class="
                absolute
                top-0
                left-0
                px-3
                py-5
                h-full
                pointer-events-none
                transform
                origin-left
                transition-all
                duration-100
                ease-in-out
              "
              >Contraseña</label
            >
            <div
              v-if="passwordErrors.length !== 0"
              class="text-sm mt-2 text-red-400 absolute"
            >
              {{ passwordErrors[0] }}
            </div>
          </div>

          <div class="mb-5 relative">
            <button
              @click="update"
              type="button"
              style="font-family: 'Quantico'"
              class="
                bg-purple-500
                text-xl
                p-3
                px-5
                rounded-xl
                text-white
                shadow-xl
                hover:shadow-inner
                focus:outline-none
                transition
                duration-500
                ease-in-out
                transform
                hover:-translate-x
                hover:scale-105
              "
            >
              GUARDAR CAMBIOS
            </button>
          </div>
        </div>
        <div>
          <hr class="mt-8" />
          <div class="grid gap-6 grid-cols-2 mt-12 mb-6 mx-auto items-center">
            <div class="floating-input mb-5 relative">
              <input
                v-model="address"
                type="text"
                id="address"
                class="
                  border-2 border-purple-500
                  focus:outline-none
                  rounded-xl
                  hover:bg-purple-100
                  focus:bg-purple-100
                  w-full
                  p-3
                  h-16
                "
                placeholder="Dirección"
                autocomplete="off"
                @blur="$v.address.$touch()"
              />
              <label
                for="address"
                class="
                  absolute
                  top-0
                  left-0
                  px-3
                  py-5
                  h-full
                  pointer-events-none
                  transform
                  origin-left
                  transition-all
                  duration-100
                  ease-in-out
                "
                >Dirección</label
              >
              <div
                v-if="addressErrors.length !== 0"
                class="text-sm mt-2 text-red-400 absolute"
              >
                {{ addressErrors[0] }}
              </div>
            </div>

            <div class="floating-input mb-5 relative">
              <input
                v-model="country"
                type="text"
                id="country"
                class="
                  border-2 border-purple-500
                  focus:outline-none
                  rounded-xl
                  hover:bg-purple-100
                  focus:bg-purple-100
                  w-full
                  p-3
                  h-16
                "
                placeholder="País"
                autocomplete="off"
                @blur="$v.country.$touch()"
              />
              <label
                for="country"
                class="
                  absolute
                  top-0
                  left-0
                  px-3
                  py-5
                  h-full
                  pointer-events-none
                  transform
                  origin-left
                  transition-all
                  duration-100
                  ease-in-out
                "
                >País</label
              >
              <div
                v-if="countryErrors.length !== 0"
                class="text-sm mt-2 text-red-400 absolute"
              >
                {{ countryErrors[0] }}
              </div>
            </div>

            <div class="floating-input mb-5 relative">
              <input
                v-model="zipCode"
                type="text"
                id="zipCode"
                class="
                  border-2 border-purple-500
                  focus:outline-none
                  rounded-xl
                  hover:bg-purple-100
                  focus:bg-purple-100
                  w-full
                  p-3
                  h-16
                "
                placeholder="Código postal"
                autocomplete="off"
                @blur="$v.zipCode.$touch()"
              />
              <label
                for="zipCode"
                class="
                  absolute
                  top-0
                  left-0
                  px-3
                  py-5
                  h-full
                  pointer-events-none
                  transform
                  origin-left
                  transition-all
                  duration-100
                  ease-in-out
                "
                >Código postal</label
              >
              <div
                v-if="zipCodeErrors.length !== 0"
                class="text-sm mt-2 text-red-400 absolute"
              >
                {{ zipCodeErrors[0] }}
              </div>
            </div>

            <div class="mb-5 relative space-x-5 space-y-3">
              <button
                @click="updateAddress"
                type="button"
                style="font-family: 'Quantico'"
                class="
                  bg-purple-500
                  text-xl
                  p-3
                  px-5
                  rounded-xl
                  text-white
                  shadow-xl
                  hover:shadow-inner
                  focus:outline-none
                  transition
                  duration-500
                  ease-in-out
                  transform
                  hover:-translate-x
                  hover:scale-105
                "
              >
                GUARDAR CAMBIOS
              </button>

              <button
                @click="$refs.modalName.openModal()"
                type="button"
                style="font-family: 'Quantico'"
                class="
                  bg-purple-500
                  text-xl
                  p-3
                  px-5
                  rounded-xl
                  text-white
                  shadow-xl
                  hover:shadow-inner
                  focus:outline-none
                  transition
                  duration-500
                  ease-in-out
                  transform
                  hover:-translate-x
                  hover:scale-105
                "
              >
                ELIMINAR
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
    <modal ref="modalName">
      <template v-slot:header>
        <h1 class="text-4xl font-semibold mb-5" style="font-family: 'Quantico'">
          ¿ELIMINAR DIRECCIÓN?
        </h1>
      </template>

      <template v-slot:body>
        <p>¿Estás seguro de que deseas eliminar tu dirección?</p>
        <p class="mt-3">Si lo haces, todas tus pujas se eliminarán</p>
        <p class="mt-3">¡Piénsalo!</p>
      </template>

      <template v-slot:footer>
        <div class="space-x-4">
          <button
            type="button"
            style="font-family: 'Quantico'"
            class="
              bg-purple-500
              text-xl
              p-3
              px-5
              rounded-xl
              text-white
              shadow-xl
              hover:shadow-inner
              focus:outline-none
              transition
              duration-500
              ease-in-out
              transform
              hover:-translate-x
              hover:scale-105
            "
            @click="
              deleteAddress();
              $refs.modalName.closeModal();
            "
          >
            ELIMINAR
          </button>
          <button
            type="button"
            style="font-family: 'Quantico'"
            class="
              bg-purple-500
              text-xl
              p-3
              px-5
              rounded-xl
              text-white
              shadow-xl
              hover:shadow-inner
              focus:outline-none
              transition
              duration-500
              ease-in-out
              transform
              hover:-translate-x
              hover:scale-105
            "
            @click="$refs.modalName.closeModal()"
          >
            CERRAR
          </button>
        </div>
      </template>
    </modal>
  </div>
</template>

<script>
const axios = require('axios');

import {validationMixin} from 'vuelidate';
import {required, sameAs} from 'vuelidate/lib/validators';
import Modal from '@/components/Modal';

const validName = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false;
  }
  return /^.{1,20}$/.test(value);
};

const validSurname = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false;
  }
  return /^.{1,30}$/.test(value);
};

const validEmail = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false;
  }
  return /^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,})$/.test(
    value
  );
};

const validPassword = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return true;
  }
  return /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,30}$/.test(
    value
  );
};

const validAddress = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false;
  }
  return /^.{10,150}$/.test(value);
};

const validCountry = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false;
  }
  return /^[A-Za-zÀ-ÖØ-öø-ÿ\s\-]{4,20}$/.test(value);
};

const validZipCode = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return false;
  }
  return /^\d{5}(?:[-\s]\d{4})?$/.test(value);
};

const validPhoneNumber = (value) => {
  if (typeof value === 'undefined' || value === null || value === '') {
    return true;
  }
  return /^(\+\d{1,3}( )?)?((\(\d{3}\))|\d{3})[- .]?\d{3}[- .]?\d{4}$|^(\+\d{1,3}( )?)?(\d{3}[ ]?){2}\d{3}$|^(\+\d{1,3}( )?)?(\d{3}[ ]?)(\d{2}[ ]?){2}\d{2}$/.test(
    value
  );
};

export default {
  name: 'UserData',
  mixins: [validationMixin],
  validations: {
    name: {required, validName},
    surname: {required, validSurname},
    email: {required, validEmail},
    password: {validPassword},
    address: {required, validAddress},
    country: {required, validCountry},
    zipCode: {required, validZipCode},
    phoneNumber: {validPhoneNumber},
  },
  components: {
    Modal,
  },
  data() {
    return {
      name: '',
      surname: '',
      email: '',
      password: '',
      address: '',
      country: '',
      zipCode: '',
      phoneNumber: '',
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      axios({url: 'http://localhost:8888/user/data', method: 'GET'})
        .then((resp) => {
          let userData = resp.data;
          this.name = userData.name;
          this.surname = userData.surname;
          this.email = userData.email;
          this.address = userData.address;
          this.country = userData.country;
          this.zipCode = userData.zipCode;
          this.phoneNumber = userData.phoneNumber;
        })
        .catch((err) => {
          console.log(err.response);
        });
    },
    update() {
      if (this.validateUpdate()) {
        const userData = {
          name: this.name,
          surname: this.surname,
          email: this.email,
          password: this.password,
          phoneNumber: this.phoneNumber,
        };
        axios({
          url: 'http://localhost:8888/user/update',
          data: userData,
          method: 'POST',
        })
          .then((resp) => {
            this.$v.$reset();
            this.$notify({
              group: 'err',
              title: 'Información actualizada',
              text: 'La información ha sido correctamente actualizada en la base de datos',
              type: 'success',
              duration: 5000,
            });
          })
          .catch((err) => {
            this.$notify({
              group: 'err',
              title: 'Error en el guardado',
              text: 'Alguno de los campos a modificar tiene un formato incorrecto',
              type: 'error',
              duration: 5000,
            });
          });
      } else {
        this.$notify({
          group: 'err',
          title: 'Error en el guardado',
          text: 'Alguno de los campos a modificar tiene un formato incorrecto',
          type: 'error',
          duration: 5000,
        });
      }
    },
    validateUpdate() {
      this.$v.name.$touch();
      this.$v.surname.$touch();
      this.$v.email.$touch();
      this.$v.password.$touch();
      this.$v.phoneNumber.$touch();
      return (
        !this.$v.name.$error &&
        !this.$v.surname.$error &&
        !this.$v.email.$error &&
        !this.$v.password.$error &&
        !this.$v.phoneNumber.$error
      );
    },
    updateAddress() {
      if (this.validateUpdateAddress()) {
        const userData = {
          address: this.address,
          country: this.country,
          zipCode: this.zipCode,
        };
        axios({
          url: 'http://localhost:8888/user/updateAddress',
          data: userData,
          method: 'POST',
        })
          .then((resp) => {
            this.$v.$reset();
            this.$notify({
              group: 'err',
              title: 'Información actualizada',
              text: 'La información ha sido correctamente actualizada en la base de datos',
              type: 'success',
              duration: 5000,
            });
          })
          .catch((err) => {
            this.$notify({
              group: 'err',
              title: 'Error en el guardado',
              text: 'Alguno de los campos de la dirección a modificar tiene un formato incorrecto',
              type: 'error',
              duration: 5000,
            });
          });
      } else {
        this.$notify({
          group: 'err',
          title: 'Error en el guardado',
          text: 'Alguno de los campos de la dirección a modificar tiene un formato incorrecto',
          type: 'error',
          duration: 5000,
        });
      }
    },
    validateUpdateAddress() {
      this.$v.address.$touch();
      this.$v.country.$touch();
      this.$v.zipCode.$touch();
      return (
        !this.$v.address.$error &&
        !this.$v.country.$error &&
        !this.$v.zipCode.$error
      );
    },
    deleteAddress() {
      axios({
        url: 'http://localhost:8888/user/deleteFullAddress',
        method: 'GET',
      })
        .then((resp) => {
          this.address = '';
          this.country = '';
          this.zipCode = '';
        })
        .catch((err) => {});
    },
  },
  computed: {
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.required && errors.push('Campo requerido.');
      !this.$v.name.validName &&
        errors.push('El nombre debe tener como máximo 20 caracteres.');
      return errors;
    },
    surnameErrors() {
      const errors = [];
      if (!this.$v.surname.$dirty) return errors;
      !this.$v.surname.required && errors.push('Campo requerido.');
      !this.$v.surname.validSurname &&
        errors.push('El apellido debe tener como máximo 30 caracteres.');
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.required && errors.push('Campo requerido.');
      !this.$v.email.validEmail && errors.push('Formato inválido');
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.validPassword &&
        errors.push(
          'Formato inválido, debe contener entre 8 y 30 caracteres, una letra minúscula, una letra mayúscula, un número y un símbolo especial'
        );
      return errors;
    },
    addressErrors() {
      const errors = [];
      if (!this.$v.address.$dirty) return errors;
      !this.$v.address.required && errors.push('Campo requerido.');
      !this.$v.address.validAddress &&
        errors.push(
          'Formato inválido, debe contener entre 10 y 150 caracteres'
        );
      return errors;
    },
    countryErrors() {
      const errors = [];
      if (!this.$v.country.$dirty) return errors;
      !this.$v.country.required && errors.push('Campo requerido.');
      !this.$v.country.validCountry &&
        errors.push('Formato inválido, debe contener entre 4 y 20 caracteres');
      return errors;
    },
    zipCodeErrors() {
      const errors = [];
      if (!this.$v.zipCode.$dirty) return errors;
      !this.$v.zipCode.required && errors.push('Campo requerido.');
      !this.$v.zipCode.validZipCode && errors.push('Formato inválido');
      return errors;
    },
    phoneNumberErrors() {
      const errors = [];
      if (!this.$v.phoneNumber.$dirty) return errors;
      !this.$v.phoneNumber.validPhoneNumber && errors.push('Formato inválido');
      return errors;
    },
  },
};
</script>

<style scoped>
img {
  width: 30vw;
}

.floating-input > input::placeholder {
  color: transparent;
}

.floating-input > input:focus,
.floating-input > input:not(:placeholder-shown) {
  @apply pt-8;
}

.floating-input > input:focus ~ label,
.floating-input > input:not(:placeholder-shown) ~ label {
  @apply opacity-75 scale-75 -translate-y-3 translate-x-1;
}

label {
  /* Chrome/Opera/Safari */
  font-family: 'Quantico';
}
</style>
