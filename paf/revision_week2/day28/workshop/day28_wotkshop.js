use bgg;

db.game.findOne()

db.game.find().limit(25).skip(0);

db.reviews.find()

db.game.aggregate([
    {
        $match: {gid: 5}
    },

    {
        $lookup: {from: "comment", foreignField: "gid", localField: "gid", as: "reviews"}
    }
])

db.comment.aggregate([
    {
        $match: {gid: 5}
    },
    {
        $group: {_id: "$gid", "average": {$avg: "$rating"}}
    }
])

db.game.aggregate([
    {
        $lookup: {from: "comment", foreignField: "gid", localField: "gid",
            pipeline:[
                
                {$sort: {rating: -1}}, {$limit: 1}
                
            ], 
            as: "reviews"}
    },
    {
        $unwind: "$reviews"
    },
    {
        $project: {"gid": 1, "name": 1, "reviews.rating": 1, "reviews.user": 1, "reviews.c_text": 1, "reviews.c_id": 1 }
    }
])

db.game.aggregate([
    {
        $lookup: {from: "comment",
            pipeline:[
                
                {$sort: {rating: -1}}, {$limit: 1}
                
            ], 
            as: "reviews"}
    },
    {
        $unwind: "$reviews"
    },
    {
        $project: {"gid": 1, "name": 1, "reviews.rating": 1, "reviews.user": 1, "reviews.c_text": 1, "reviews.c_id": 1 }
    }
])













