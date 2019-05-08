import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysCoupon } from 'app/shared/model/sys-coupon.model';
import { SysCouponService } from './sys-coupon.service';

@Component({
    selector: 'jhi-sys-coupon-delete-dialog',
    templateUrl: './sys-coupon-delete-dialog.component.html'
})
export class SysCouponDeleteDialogComponent {
    sysCoupon: ISysCoupon;

    constructor(
        protected sysCouponService: SysCouponService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysCouponService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysCouponListModification',
                content: 'Deleted an sysCoupon'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-coupon-delete-popup',
    template: ''
})
export class SysCouponDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysCoupon }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysCouponDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sysCoupon = sysCoupon;
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
