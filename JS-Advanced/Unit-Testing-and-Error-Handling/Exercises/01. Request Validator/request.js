function solve(obj) {
    let methods = ['GET', 'POST', 'DELETE', 'CONNECT'];
    let uri = /^([A-Za-z\d\.]+|\*)$/g;
    let versions = ['HTTP/0.9', 'HTTP/1.0', 'HTTP/1.1', 'HTTP/2.0'];
    let message = /^([^<>\\&'"]*)$/g;
    
    
    
    if(!methods.includes(obj.method) || !obj.hasOwnProperty('method')){
        throw new Error ('Invalid request header: Invalid Method');
    }
    if(!uri.test(obj.uri) || !obj.hasOwnProperty('uri')) {
        throw new Error ('Invalid request header: Invalid URI');
    }
    if(!versions.includes(obj.version) || !obj.hasOwnProperty('version')) {
        throw new Error ('Invalid request header: Invalid Version');
    }
    if(!message.test(obj.message) || !obj.hasOwnProperty('message')) {
        throw new Error ('Invalid request header: Invalid Message');
    }

    return obj;
}

console.log(solve({
    method: 'GET',
    uri: 'svn.public.catalog',
    version: 'HTTP/1.1',
    message: ''
  }));
