import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysReview } from 'app/shared/model/sys-review.model';
import { SysReviewService } from './sys-review.service';

@Component({
    selector: 'jhi-sys-review-delete-dialog',
    templateUrl: './sys-review-delete-dialog.component.html'
})
export class SysReviewDeleteDialogComponent {
    sysReview: ISysReview;

    constructor(
        protected sysReviewService: SysReviewService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysReviewService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysReviewListModification',
                content: 'Deleted an sysReview'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-review-delete-popup',
    template: ''
})
export class SysReviewDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysReview }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysReviewDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sysReview = sysReview;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
