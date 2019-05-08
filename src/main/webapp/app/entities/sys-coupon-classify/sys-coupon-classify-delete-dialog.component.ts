import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';
import { SysCouponClassifyService } from './sys-coupon-classify.service';

@Component({
    selector: 'jhi-sys-coupon-classify-delete-dialog',
    templateUrl: './sys-coupon-classify-delete-dialog.component.html'
})
export class SysCouponClassifyDeleteDialogComponent {
    sysCouponClassify: ISysCouponClassify;

    constructor(
        protected sysCouponClassifyService: SysCouponClassifyService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysCouponClassifyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysCouponClassifyListModification',
                content: 'Deleted an sysCouponClassify'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-coupon-classify-delete-popup',
    template: ''
})
export class SysCouponClassifyDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCouponClassify }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysCouponClassifyDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysCouponClassify = sysCouponClassify;
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
