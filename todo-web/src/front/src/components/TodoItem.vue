<template>
    <div>
        {{todoItem.id}}
        {{todoItem.contents}}
        {{todoItem.status}}
    </div>
</template>

<script>
    export default {
        name: "TodoItem",
        props: {
            todoItem: {
                type: Object,
                required: true
            }
        }
    }
</script>

<style scoped>

</style>