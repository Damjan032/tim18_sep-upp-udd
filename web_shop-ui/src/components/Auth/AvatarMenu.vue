<template>
    <v-menu
            v-model="menuActive"
            :close-on-content-click="false"
            :nudge-width="250"
            z-index="14"
            offset-y
    >
        <template v-slot:activator="{ on }">
            <v-btn icon class="ml-1 mr-2" v-on="on">
                <v-avatar size="37" color="primary">
                    <span class="white--text subtitle-1">{{ avatarInitials }}</span>
                </v-avatar>
            </v-btn>
        </template>
        <v-card outlined elevation="6">
            <v-list>
                <v-list-item class="mt-5">
                    <v-avatar class="ma-auto" color="primary" size="96">
                        <span class="white--text display-2">{{ avatarInitials }}</span>
                    </v-avatar>
                </v-list-item>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title v-if="user" class="d-flex justify-center">
                            {{ user.firstName }} {{ user.lastName }}
                        </v-list-item-title>
                        <v-list-item-subtitle v-if="user" class="d-flex justify-center">
                            {{ user.email }}
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>
                <v-list-item>
                    <v-btn color="grey darken-2" class="mx-auto" @click="btLogout" outlined>
                        Sign out
                    </v-btn>
                </v-list-item>

            </v-list>
            <v-divider></v-divider>
            <v-card-actions class="pa-0">
                <v-container class="pa-2 justify-center text-center">
                    <v-btn text x-small>
                        WebShop
                    </v-btn>
                    <span> • </span>
                    <v-btn text x-small>
                        Terms of service
                    </v-btn>
                </v-container>
            </v-card-actions>
        </v-card>
    </v-menu>
</template>

<script>
    import {mapActions, mapState} from "vuex";

    export default {
        name: "AvatarMenu",
        data: () => ({
            menuActive: false,
        }),
        computed: {
            ...mapState('auth', ['user']),
            avatarInitials() {
                if (!this.user) return '';
                let firstName = this.user.email;
                let firstNameInitial = firstName ? firstName[0].toUpperCase() : 'A';
                return `${firstNameInitial}`;
            }
        },
        methods: {
            ...mapActions('auth', ['logout']),
            ...mapActions('basket', ['deleteBasketAllApi']),
            btLogout() {
                this.deleteBasketAllApi();
                this.logout();
            },
            toProfile() {
                this.$router.push(`/profile/${this.user.id}`);
                this.menuActive = false;
            }
        }
    }
</script>

<style scoped>

</style>
