db = db.getSiblingDB('admin');
db.auth('root', '12345678');  // Autenticación del usuario administrador

// Cambiar a la base de datos 'chat_app'
db = db.getSiblingDB('chat_app');

// Crear un usuario con permisos de lectura y escritura
db.createUser({
    user: 'chat_messages',
    pwd: '12345678',
    roles: [
        {
            role: 'readWrite',
            db: 'chat_app',  // Permisos sobre la base de datos chat_app
        },
    ],
});

db.createCollection('test_docker');  // Crear una colección de ejemplo
