/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponDeleteDialogComponent } from 'app/entities/sys-coupon/sys-coupon-delete-dialog.component';
import { SysCouponService } from 'app/entities/sys-coupon/sys-coupon.service';

describe('Component Tests', () => {
    describe('SysCoupon Management Delete Component', () => {
        let comp: SysCouponDeleteDialogComponent;
        let fixture: ComponentFixture<SysCouponDeleteDialogComponent>;
        let service: SysCouponService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponDeleteDialogComponent]
            })
                .overrideTemplate(SysCouponDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCouponDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
