// 파일 선택 시 이벤트 처리
document.getElementById('fileInput').addEventListener('change', function(event) {
    setThumbnail(event, 5); // 최대 5개의 이미지 파일 선택 가능
});

// 파일 유효성 검사
function isValidImageFile(file) {
    return file.type.startsWith('image/');
}

// 파일 크기 제한 (1MB)
function isValidImageFileSize(file, maxSizeInBytes) {
    return file.size <= maxSizeInBytes;
}

// 파일 미리보기 및 업로드
function setThumbnail(event, maxFiles) {
    var container = document.getElementById("image_container");
    container.innerHTML = "";

    var validFileCount = 0;

    for (var i = 0; i < event.target.files.length; i++) {
        var file = event.target.files[i];

        if (!isValidImageFile(file)) {
            alert(`File ${file.name} is not a valid image.`);
            continue;
        }

        if (!isValidImageFileSize(file, 10485670)) { // 10MB
            alert(`File ${file.name} is too large. Maximum size is 10MB.`);
            continue;
        }

        if (validFileCount >= maxFiles) {
            alert(`You can only select up to ${maxFiles} files.`);
            break;
        }

        var img = document.createElement("img");
        img.setAttribute("src", URL.createObjectURL(file));
        img.style.maxWidth = "150px";
        img.style.maxHeight = "150px";
        img.style.margin = "0 10px";
        container.appendChild(img);
        
        validFileCount++;
    }
}

// 파일 업로드 버튼 클릭 시 서버로 이미지 업로드
document.getElementById('uploadButton').addEventListener('click', function() {
    var selectedFiles = document.getElementById('fileInput').files;
    if (selectedFiles.length > 0) {
        uploadImages(selectedFiles);
    } else {
        alert("No images selected for upload.");
    }
});

// 이미지 파일 업로드 (서버로 전송하는 코드는 서버 백엔드 기술에 따라 다릅니다)
function uploadImages(files) {
    const formData = new FormData();
    for (let i = 0; i < files.length; i++) {
        formData.append('image', files[i]);
    }

    // 서버로 Ajax 요청을 보내는 예시 (서버에서는 이미지를 처리하는 로직이 필요)
    fetch('/upload', {
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (response.ok) {
            alert('Images uploaded successfully.');
        } else {
            alert('Failed to upload images.');
        }
    })
    .catch(error => {
        console.error('Error during image upload:', error);
    });
}
