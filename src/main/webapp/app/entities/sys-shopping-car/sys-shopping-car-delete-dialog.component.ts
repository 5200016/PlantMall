import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';
import { SysShoppingCarService } from './sys-shopping-car.service';

@Component({
    selector: 'jhi-sys-shopping-car-delete-dialog',
    templateUrl: './sys-shopping-car-delete-dialog.component.html'
})
export class SysShoppingCarDeleteDialogComponent {
    sysShoppingCar: ISysShoppingCar;

    constructor(
        protected sysShoppingCarService: SysShoppingCarService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysShoppingCarService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysShoppingCarListModification',
                content: 'Deleted an sysShoppingCar'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-shopping-car-delete-popup',
    template: ''
})
export class SysShoppingCarDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysShoppingCar }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysShoppingCarDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysShoppingCar = sysShoppingCar;
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
