/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingProductDeleteDialogComponent } from 'app/entities/sys-shopping-product/sys-shopping-product-delete-dialog.component';
import { SysShoppingProductService } from 'app/entities/sys-shopping-product/sys-shopping-product.service';

describe('Component Tests', () => {
    describe('SysShoppingProduct Management Delete Component', () => {
        let comp: SysShoppingProductDeleteDialogComponent;
        let fixture: ComponentFixture<SysShoppingProductDeleteDialogComponent>;
        let service: SysShoppingProductService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingProductDeleteDialogComponent]
            })
                .overrideTemplate(SysShoppingProductDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysShoppingProductDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysShoppingProductService);
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
