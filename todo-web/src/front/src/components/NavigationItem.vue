<template>
    <span>
        {{title.id}}
    </span>
</template>

<script>
    export default {
        name: "NavigationItem",
        props: {
            title: {
                type: Object,
                required: true
            }
        }
    }
</script>

<style scoped>

</style>