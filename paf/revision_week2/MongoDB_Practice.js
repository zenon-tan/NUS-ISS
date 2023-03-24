use bgg;

db.game.findOne();
db.game.find({gid: 6});
db.game.find({year: 1989});

db.game.find({
    name: {$regex: "mare", $options: "i"}
});

db.game.find({name: /.*mare.*/})

db.game.find({_id: ObjectId("63feb115fdf8d2c724a81ed2")});

db.game.find({year: {$gte: 1989}, ranking: {$lte: 2000}}).sort({ranking: 1}).limit(1);

db.game.find({year: {$gte: 2000}}).count();

db.game.find({year: {$gte: 1989}, ranking: {$lte: 2000}}, {_id: 0, name: 1}).sort({ranking: 1}).limit(1);

db.reviews.findOne()

db.reviews.insert(
    {
        user: "Jesus",
        c_id: "66gg55fd",
        rating: 10,
        comment: "Yass jesus",
        ID: 5,
        posted: ISODate("2023-02-22T08:29:41.534+0000"),
        name: "Acquire"
    }
)

db.reviews.deleteOne({
    _id: ObjectId("63feb76b2a1227d2226bc292")
})

db.comment.findOne();

db.comment.aggregate([
    {
        $match: {rating: 6}
    },
    {
        $group: {_id: "$gid", count: {$sum: 1}, users: {$push: "$user"}}
    },
    {
        $sort: {count: -1}
    }
])

db.comment.aggregate([
    {
        $bucket: {groupBy: "$rating", boundaries: [0, 3, 5, 8, 10], default: "Others", output: {count: {$sum: 1}, users: {$push: "$user"}}}
    }
])

db.game.aggregate([
    {
        $lookup: {from: "comment", foreignField: 'gid', localField: 'gid', as: 'reviews'}
    }
])

db.comment.aggregate([
    {
        $lookup: {from: "game", foreignField: 'gid', localField: 'gid', as: 'game'}
    }
])













