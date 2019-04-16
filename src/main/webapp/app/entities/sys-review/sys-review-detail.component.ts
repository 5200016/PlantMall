import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysReview } from 'app/shared/model/sys-review.model';

@Component({
    selector: 'jhi-sys-review-detail',
    templateUrl: './sys-review-detail.component.html'
})
export class SysReviewDetailComponent implements OnInit {
    sysReview: ISysReview;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysReview }) => {
            this.sysReview = sysReview;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
