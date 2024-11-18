db = db.getSiblingDB('admin');
db.auth('root', '12345678');

db = db.getSiblingDB('chat_app');
db.createUser({
    user: 'app_user',
    pwd: 'password',
    roles: [
        {
            role: 'readWrite',
            db: 'chat_app',
        },
    ],
});

db.createCollection('test_docker');