<template>
    <div>
        <NavigationItem
                :key="title.id"
                :title="title"
                v-for="title in titles"
        />
    </div>
</template>

<script>
    import NavigationItem from "@/components/NavigationItem";

    export default {
        name: "NavigationContainer",
        data() {
            return {
                titles: [
                    {
                        id: 'todo'
                    },
                    {
                        id: 'inProgress'
                    },
                    {
                        id: 'done'
                    }
                ]
            }
        },
        components: {
            NavigationItem
        }
    }
</script>

<style scoped>

</style>