function solution(command) {

    let commands = {
        'upvote': () => this.upvotes++,
        'downvote': () => this.downvotes++,
        'score': () => {
            let upvotes = this.upvotes;
            let downvotes = this.downvotes;

            let totalVotes = upvotes + downvotes;
            let balance = upvotes - downvotes;

            let inflationAmount = Math.ceil(Math.max(upvotes, downvotes) * 0.25);

            let reportedUpvotes = totalVotes > 50 ? upvotes + inflationAmount : upvotes;
            let reportedDownvotes = totalVotes > 50 ? downvotes + inflationAmount : downvotes;

            let rating = 'new';

            if (totalVotes < 10) {
                rating = 'new';
            } else if (upvotes / totalVotes > 0.66) {
                rating = 'hot';
            } else if (balance >= 0 && totalVotes > 100) {
                rating = 'controversial';
            } else if (balance < 0) {
                rating = 'unpopular';
            }

            return [reportedUpvotes, reportedDownvotes, balance, rating];
        }
    }

    return commands[command](); 
}


let post = {
    id: '3',
    author: 'emil',
    content: 'wazaaaaa',
    upvotes: 100,
    downvotes: 100
};
solution.call(post, 'upvote');
solution.call(post, 'downvote');
let score = solution.call(post, 'score'); // [127, 127, 0, 'controversial']
console.log(score);
solution.call(post, 'downvote');        
score = solution.call(post, 'score'); 
console.log(score);
